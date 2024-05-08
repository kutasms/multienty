package com.chia.multienty.wechat.thirdparty.sdk.tools;

import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.dto.WechatMppNickNameAuditDTO;
import com.chia.multienty.core.domain.dto.WechatMppRegisterAuditDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.parameter.wechat.*;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.pojo.WechatAppFuncScope;
import com.chia.multienty.core.pojo.WechatMppAuthTask;
import com.chia.multienty.core.pojo.WechatMppCodeAudit;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.*;
import com.chia.multienty.core.util.TimeUtil;
import com.chia.multienty.wechat.thirdparty.define.WxThirdPartyConstants;
import com.chia.multienty.wechat.thirdparty.define.baseinfo.MppCreatingMode;
import com.chia.multienty.wechat.thirdparty.define.event.*;
import com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo.AccountBasicInfoGetRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.platform.AuthorizationInfoGetByAuthCodeRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.AccountBasicInfoGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizationInfoGetByAuthCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class WxTPReceiver {
    @Autowired
    private StringRedisService stringRedisService;
    @Autowired
    private WechatAppService wechatAppService;
    @Autowired
    private WechatAppFuncScopeService wechatAppFuncScopeService;
    @Autowired
    private WechatMppNickNameAuditService wechatMppNickNameAuditService;
    @Autowired
    private WechatMppCodeAuditService wechatMppCodeAuditService;
    @Autowired
    private WechatMppRegisterAuditService wechatMppRegisterAuditService;
    @Autowired
    private WechatMppAuthTaskService wechatMppAuthTaskService;
    @Autowired
    private YamlMultientyProperties properties;
    @Autowired
    private WxTPApiExecutor wxTPApiExecutor;
    @Autowired
    private WxTPTokenProvider tokenProvider;

    private final static Map<Integer, List<String>> testAccountMap;

    static  {
        testAccountMap = new HashMap<>();
        testAccountMap.put(0, Arrays.asList(
                "wx570bc396a51b8ff8",
                "wx9252c5e0bb1836fc",
                "wx8e1097c5bc82cde9",
                "wx14550af28c71a144",
                "wxa35b9c23cfe664eb"
        ));
        testAccountMap.put(1,Arrays.asList(
                "wxd101a85aa106f53e",
                "wxc39235c15087f6f3",
                "wx7720d01d4b2a4500",
                "wx05d483572dcd5d8b",
                "wx5910277cae6fd970"
        ));
    }

    /**
     * 是否微信官方测试账号
     * @param appId
     * @return
     */
    private static boolean isWxTestAccount(String appId) {
        return testAccountMap.get(0).stream().anyMatch(p->p.equals(appId)) ||
                testAccountMap.get(1).stream().anyMatch(p-> p.equals(appId));
    }
    /**
     * 接收来自微信服务器的component_verify_ticket推送事件
     * @return
     */
    public String handleVerifyTicketReceived(String decryptedMsg) {
        ComponentVerifyTicketPushEvent event = XmlKit.parse(decryptedMsg, ComponentVerifyTicketPushEvent.class);
        stringRedisService.set(WxThirdPartyConstants.CACHE_KEY_COMPONENT_VERIFY_TICKET, event.getTicket(), 11 * 60 * 60 * 1000);
        return StatusEnum.SUCCESS.name().toLowerCase();
    }

    /**
     * 接收来自微信服务器的授权变更事件
     * @return
     */
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = { java.lang.Exception.class})
    public String handleAuthorizationUpdateReceived(String decryptedMsg) {
        AuthorizationUpdateEvent event = XmlKit.parse(decryptedMsg, AuthorizationUpdateEvent.class);

        if(event.getInfoType().equals("unauthorized")) {
            WechatApp app = wechatAppService.getByAppId(event.getAuthorizerAppId());
            if(app != null) {
                wechatAppService.delete(new WechatAppDeleteParameter()
                        .setProgramId(app.getProgramId())
                        .setTenantId(app.getTenantId())
                );
                wechatAppFuncScopeService.delete(app.getTenantId(), app.getProgramId());
            }
        } else if(event.getInfoType().equals("updateauthorized")) {
            // TODO: 需要对授权更新进行相关处理
            //更新权限集
            WechatApp app = wechatAppService.getByAppId(event.getAppId());
            AuthorizationInfoGetByAuthCodeRequest request = new AuthorizationInfoGetByAuthCodeRequest();
            request.setAuthorizationCode(app.getAuthorizationCode());
            request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
            AuthorizationInfoGetByAuthCodeResponse response = wxTPApiExecutor.execute(request);
            wechatAppFuncScopeService.delete(app.getTenantId(), app.getProgramId());
            List<WechatAppFuncScope> funcScopes = response.getAuthorizationInfo()
                    .getFuncInfo()
                    .stream()
                    .map(m -> new WechatAppFuncScope()
                            .setProgramId(app.getProgramId())
                            .setType(m.getFuncScopeCategory().getType())
                            .setTenantId(app.getTenantId())
                            .setFuncScopeId(m.getFuncScopeCategory().getId())
                    )
                    .collect(Collectors.toList());
            wechatAppFuncScopeService.saveBatchTE(funcScopes);
        } else if(event.getInfoType().equals("authorized")) {
            WechatApp app = wechatAppService.getByPreAuthCode(event.getPreAuthCode());
            if(app == null) {
                if(isWxTestAccount(event.getAuthorizerAppId())) {
                    log.info("官方测试账号:{}", event.getAuthorizerAppId());
                    app = new WechatApp();
                }
            }
            app.setAuthorizationCode(event.getAuthorizationCode());
            app.setPreAuthCode(event.getPreAuthCode());
            app.setMiniAppId(event.getAuthorizerAppId());
            AuthorizationInfoGetByAuthCodeRequest request = new AuthorizationInfoGetByAuthCodeRequest();
            request.setAuthorizationCode(app.getAuthorizationCode());
            request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
            AuthorizationInfoGetByAuthCodeResponse response = wxTPApiExecutor.execute(request);
            app.setAuthorizerAccessToken(response.getAuthorizationInfo().getAuthorizerAccessToken());
            app.setAuthorizerRefreshToken(response.getAuthorizationInfo().getAuthorizerRefreshToken());
            app.setAuthorizerTokenTime(LocalDateTime.now());
            app.setAuthorizerTokenExpiresIn(response.getAuthorizationInfo().getExpiresIn());
            app.setStatus(StatusEnum.AUTHORIZED.getCode());

            AccountBasicInfoGetRequest accountBasicInfoGetRequest = new AccountBasicInfoGetRequest();
            accountBasicInfoGetRequest.setAccessToken(response.getAuthorizationInfo().getAuthorizerAccessToken());
            AccountBasicInfoGetResponse accountBasicInfoGetResponse = wxTPApiExecutor.execute(accountBasicInfoGetRequest);
            app.setAppNickName(accountBasicInfoGetResponse.getNickName());
            app.setAccountType(accountBasicInfoGetResponse.getAccountType().getValue().byteValue());
            app.setHeadImageUrl(accountBasicInfoGetResponse.getHeadImageInfo().getHeadImageUrl());
            app.setPrincipalName(accountBasicInfoGetResponse.getPrincipalName());
            app.setRealnameStatus(accountBasicInfoGetResponse.getRealNameStatus().getValue().byteValue());
            app.setQualificationVerify(accountBasicInfoGetResponse.getWxVerifyInfo().getQualificationVerify());
            if(isWxTestAccount(event.getAuthorizerAppId())) {
                wechatAppService.save(app);
            } else {
                WechatAppUpdateParameter updateParameter = new WechatAppUpdateParameter();
                BeanUtils.copyProperties(app, updateParameter);
                wechatAppService.update(updateParameter);
            }

            //更新权限集
            wechatAppFuncScopeService.delete(app.getTenantId(), app.getProgramId());
            WechatApp finalApp = app;
            List<WechatAppFuncScope> funcScopes = response.getAuthorizationInfo()
                    .getFuncInfo()
                    .stream()
                    .map(m -> new WechatAppFuncScope()
                            .setProgramId(finalApp.getProgramId())
                            .setType(m.getFuncScopeCategory().getType())
                            .setTenantId(finalApp.getTenantId())
                            .setFuncScopeId(m.getFuncScopeCategory().getId())
                    )
                    .collect(Collectors.toList());
            wechatAppFuncScopeService.saveBatchTE(funcScopes);
        }
        return StatusEnum.SUCCESS.name().toLowerCase();
    }


    /**
     * 接收来自微信服务器的昵称设置事件
     * @return
     */
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = {
            Exception.class})
    public String handleNickNameSetEventReceived(String decryptedMsg) {
        NickNameSetEvent event = XmlKit.parse(decryptedMsg, NickNameSetEvent.class);
        log.info("收到昵称设置事件:{}", JSONObject.toJSONString(event, true));
        WechatMppNickNameAuditDTO audit = wechatMppNickNameAuditService.getDTOByOpenAuditId(event.getFrom());
        if(audit != null) {
            if(event.getRet().equals(3)) {
                audit.setStatus(StatusEnum.SUCCESS.getCode());
                WechatApp app = wechatAppService.getBy(audit.getTenantId(), audit.getAppId());
                app.setAppNickName(audit.getNickName());
                wechatAppService.save(app);
            } else {
                audit.setStatus(StatusEnum.FAILURE.getCode());
                audit.setReason(event.getReason());
            }
        }
        WechatMppNickNameAuditUpdateParameter parameter = new WechatMppNickNameAuditUpdateParameter();
        BeanUtils.copyProperties(audit, parameter);
        wechatMppNickNameAuditService.update(parameter);
        return StatusEnum.SUCCESS.name().toLowerCase();
    }

    /**
     * 接收来自微信服务器的代码审核事件
     * @return
     */
    public String handleCodeAuditEventReceived(String decryptedMsg) {
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
            WechatMppCodeAuditUpdateParameter parameter = new WechatMppCodeAuditUpdateParameter();
            BeanUtils.copyProperties(audit, parameter);
            wechatMppCodeAuditService.update(parameter);
        }

        return StatusEnum.SUCCESS.name().toLowerCase();
    }

    /**
     * 接收来自微信服务器的小程序认证事件
     * @return
     */
    public String handleMppAuthenticationTaskEventReceived(String decryptedMsg) {
        // event = notify_3rd_wxa_auth
        log.info("收到小程序认证通知事件:{}", decryptedMsg);
        MppAuthenticationTaskEvent event = XmlKit.parse(decryptedMsg, MppAuthenticationTaskEvent.class);
        WechatMppAuthTask task = wechatMppAuthTaskService.getBy(event.getAppId());
        task.setTaskStatus(event.getTaskStatus());
        task.setApplyStatus(event.getApplyStatus());
        if(task.getApplyStatus().equals(2)) {
            task.setDispatchInfoContact(event.getDispatchInfoContact());
            task.setDispatchInfoDispatchTime(event.getDispatchTime().longValue());
            task.setDispatchInfoProvider(event.getDispatchInfoProvider());
        }
        task.setMessage(event.getMessage());
        WechatMppAuthTaskUpdateParameter parameter = new WechatMppAuthTaskUpdateParameter();
        BeanUtils.copyProperties(task, parameter);
        wechatMppAuthTaskService.update(parameter);
        return StatusEnum.SUCCESS.name().toLowerCase();
    }

    /**
     * 处理微信小程序注册审核事件
     * @return
     */
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = {
            Exception.class})
    public String handleMppRegisterAuditEventReceived(String decryptedMsg) {
        log.info("收到小程序注册审核事件:{}", decryptedMsg);
        MppRegisterAuditEvent event = XmlKit.parse(decryptedMsg, MppRegisterAuditEvent.class);
        WechatMppRegisterAuditDTO audit = wechatMppRegisterAuditService.getBy(event.getInfo().getName(),
                event.getInfo().getCode(),
                event.getInfo().getLegalPersonaWechat(),
                event.getInfo().getLegalPersonaName());
        if(audit != null) {
            audit.setStatus(event.getStatus().toString());
            audit.setAuthCode(event.getAuthCode());
            audit.setMessage(event.getMsg());
        }
        WechatMppRegisterAuditUpdateParameter parameter = new WechatMppRegisterAuditUpdateParameter();
        BeanUtils.copyProperties(audit, parameter);
        wechatMppRegisterAuditService.update(parameter);
        if(event.getStatus().equals(0)) {
            //注册成功,新增应用数据
            WechatApp wechatApp = new WechatApp();
            wechatApp.setStatus(StatusEnum.CREATED.getCode());
            wechatApp.setCreateMode(MppCreatingMode.REG_ENTERPRISE_MPP.getValue().byteValue());
            wechatApp.setTenantId(audit.getTenantId());
            wechatApp.setMiniAppId(event.getAppId());

            wechatAppService.save(wechatApp);
        }
        return StatusEnum.SUCCESS.name().toLowerCase();
    }

    public String handleBetaMppRegisterEventReceived(String decryptedMsg) {
        log.info("收到试用小程序注册事件:{}", decryptedMsg);
        BetaMppRegisterEvent event = XmlKit.parse(decryptedMsg, BetaMppRegisterEvent.class);
        WechatApp app = wechatAppService.getByUniqueId(event.getInfo().getUniqueId());
        if(event.getStatus().equals(0)) {
            app.setMiniAppId(event.getAppId());
        }


        return StatusEnum.SUCCESS.name().toLowerCase();
    }

    public String handleBataMppVerifyEventReceived(String decryptedMsg) {
        log.info("收到试用小程序转正审核事件:{}", decryptedMsg);
        MppRegisterAuditEvent event = XmlKit.parse(decryptedMsg, MppRegisterAuditEvent.class);
        WechatMppRegisterAuditDTO audit = wechatMppRegisterAuditService.getBy(event.getInfo().getName(),
                event.getInfo().getCode(),
                event.getInfo().getLegalPersonaWechat(),
                event.getInfo().getLegalPersonaName());
        if(audit != null) {
            audit.setStatus(event.getStatus().toString());
            audit.setAuthCode(event.getAuthCode());
            audit.setMessage(event.getMsg());
        }
        WechatMppRegisterAuditUpdateParameter parameter = new WechatMppRegisterAuditUpdateParameter();
        BeanUtils.copyProperties(audit, parameter);
        wechatMppRegisterAuditService.update(parameter);
        if(event.getStatus().equals(0)) {
            //注册成功,新增应用数据
            WechatApp wechatApp = new WechatApp();
            wechatApp.setStatus(StatusEnum.CREATED.getCode());
            wechatApp.setTenantId(audit.getTenantId());
            wechatApp.setMiniAppId(event.getAppId());
            wechatAppService.save(wechatApp);
        }
        return StatusEnum.SUCCESS.name().toLowerCase();
    }

}
