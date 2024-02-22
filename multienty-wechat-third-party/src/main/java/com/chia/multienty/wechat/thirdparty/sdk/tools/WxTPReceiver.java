package com.chia.multienty.wechat.thirdparty.sdk.tools;

import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.pojo.WechatAppFuncScope;
import com.chia.multienty.core.pojo.WechatMppCodeAudit;
import com.chia.multienty.core.pojo.WechatMppNickNameAudit;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.service.*;
import com.chia.multienty.core.util.TimeUtil;
import com.chia.multienty.wechat.thirdparty.define.WxThirdPartyConstants;
import com.chia.multienty.wechat.thirdparty.define.event.*;
import com.chia.multienty.wechat.thirdparty.sdk.request.platform.AuthorizationInfoGetByAuthCodeRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizationInfoGetByAuthCodeResponse;
import com.chia.multienty.wechat.thirdparty.define.event.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class WxTPReceiver {
    private final StringRedisService stringRedisService;

    private final WechatAppService wechatAppService;

    private final WechatAppFuncScopeService wechatAppFuncScopeService;

    private final WechatMppNickNameAuditService wechatMppNickNameAuditService;

    private final WechatMppCodeAuditService wechatMppCodeAuditService;

    private final WechatMppRegisterAuditService wechatMppRegisterAuditService;

    private final YamlMultiTenantProperties properties;

    private final WxTPApiExecutor wxTPApiExecutor;
    /**
     * 接收来自微信服务器的component_verify_ticket推送事件
     * @param encryptedXml
     * @return
     */
    public String handleVerifyTicketReceived(String encryptedXml) {
        String decryptedMsg = WxTPSecurityTool.decrypt(encryptedXml);
        ComponentVerifyTicketPushEvent event = XmlKit.parse(decryptedMsg, ComponentVerifyTicketPushEvent.class);
        stringRedisService.set(WxThirdPartyConstants.CACHE_KEY_COMPONENT_VERIFY_TICKET, event.getTicket());
        return StatusEnum.SUCCESS.name().toLowerCase();
    }

    /**
     * 接收来自微信服务器的授权变更事件
     * @param encryptedXml
     * @return
     */
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = { java.lang.Exception.class})
    public String handleAuthUpdateReceived(String encryptedXml) {
        String decryptedMsg = WxTPSecurityTool.decrypt(encryptedXml);
        AuthUpdateEvent event = XmlKit.parse(encryptedXml, AuthUpdateEvent.class);
        WechatApp app = wechatAppService.getByPreAuthCode(event.getPreAuthCode());
        if(event.getInfoType().equals("authorized") || event.getInfoType().equals("updateauthorized")) {
            app.setAuthorizationCode(event.getAuthorizationCode());
            app.setPreAuthCode(event.getPreAuthCode());

            AuthorizationInfoGetByAuthCodeRequest request = new AuthorizationInfoGetByAuthCodeRequest();
            request.setAuthorizationCode(app.getAuthorizationCode());
            request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
            AuthorizationInfoGetByAuthCodeResponse response = wxTPApiExecutor.execute(request);
            app.setAuthorizerAccessToken(response.getAuthorizationInfo().getAuthorizerAccessToken());
            app.setAuthorizerRefreshToken(response.getAuthorizationInfo().getAuthorizerRefreshToken());
            app.setAuthorizerTokenTime(LocalDateTime.now());
            app.setAuthorizerTokenExpiresIn(response.getAuthorizationInfo().getExpiresIn());
            app.setStatus(StatusEnum.AUTHORIZED.getCode());
            wechatAppService.updateByIdTE(app);

            wechatAppFuncScopeService.deleteByProgramId(app.getProgramId());
            List<WechatAppFuncScope> funcScopes = response.getAuthorizationInfo()
                    .getFuncInfo()
                    .stream()
                    .map(m -> new WechatAppFuncScope()
                            .setProgramId(app.getProgramId())
                            .setType(m.getFuncScopeCategory().getType())
                            .setFuncScopeId(m.getFuncScopeCategory().getId())
                    )
                    .collect(Collectors.toList());
            wechatAppFuncScopeService.saveBatchTE(funcScopes);
        }
        return StatusEnum.SUCCESS.name().toLowerCase();
    }


    /**
     * 接收来自微信服务器的昵称设置事件
     * @param encryptedXml
     * @return
     */
    public String handleNickNameSetEventReceived(String encryptedXml) {
        String decryptedMsg = WxTPSecurityTool.decrypt(encryptedXml);
        NickNameSetEvent event = XmlKit.parse(decryptedMsg, NickNameSetEvent.class);
        log.info("收到昵称设置事件:{}", JSONObject.toJSONString(event, true));
        WechatMppNickNameAudit audit = wechatMppNickNameAuditService.getByNickName(event.getNickName());
        if(audit != null) {
            if(event.getRet().equals(3)) {
                audit.setStatus(StatusEnum.SUCCESS.getCode());
            } else {
                audit.setStatus(StatusEnum.FAILURE.getCode());
                audit.setReason(event.getReason());
            }
        }
        wechatMppNickNameAuditService.updateByIdTE(audit);
        return StatusEnum.SUCCESS.name().toLowerCase();
    }

    /**
     * 接收来自微信服务器的代码审核事件
     * @param encryptedXml
     * @return
     */
    public String handleCodeAuditEventReceived(String encryptedXml) {
        String decryptedMsg = WxTPSecurityTool.decrypt(encryptedXml);
        CodeAuditEvent event = XmlKit.parse(decryptedMsg, CodeAuditEvent.class);
        WechatMppCodeAudit audit = wechatMppCodeAuditService.getByAppId(event.getTo());
        if(audit != null) {
            switch (event.getEvent()) {
                case "weapp_audit_success":
                    audit.setSuccessTime(TimeUtil.parseTimestamp(event.getSuccessTime()));
                    audit.setStatus(StatusEnum.SUCCESS.getCode());
                    break;
                case "weapp_audit_fail":
                    audit.setFailTime(TimeUtil.parseTimestamp(event.getFailTime()));
                    audit.setStatus(StatusEnum.FAILURE.getCode());
                    audit.setReason(event.getReason());
                    audit.setScreenShot(event.getScreenShot());
                    break;
                case "weapp_audit_delay":
                    audit.setDelayTime(TimeUtil.parseTimestamp(event.getDelayTime()));
                    audit.setStatus(StatusEnum.DELETED.getCode());
                    break;
            }
            wechatMppCodeAuditService.updateByIdTE(audit);
        }

        return StatusEnum.SUCCESS.name().toLowerCase();
    }

    /**
     * 接收来自微信服务器的小程序认证事件
     * @param encryptedXml
     * @return
     */
    public String handleMppAuditEventReceived(String encryptedXml) {
        String decryptedMsg = WxTPSecurityTool.decrypt(encryptedXml);
        MppAuditEvent event = XmlKit.parse(decryptedMsg, MppAuditEvent.class);
        return StatusEnum.SUCCESS.name().toLowerCase();
    }
}
