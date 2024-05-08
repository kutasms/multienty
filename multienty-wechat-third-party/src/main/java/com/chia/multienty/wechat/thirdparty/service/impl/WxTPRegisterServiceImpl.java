package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.pojo.WechatBetaMppAuthAudit;
import com.chia.multienty.core.pojo.WechatBetaMppRegisterAudit;
import com.chia.multienty.core.pojo.WechatMppRegisterAudit;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.*;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.core.util.QRCodeUtil;
import com.chia.multienty.wechat.oauth.define.WechatOAuthResult;
import com.chia.multienty.wechat.oauth.service.OAuthService;
import com.chia.multienty.wechat.thirdparty.define.baseinfo.MppCreatingMode;
import com.chia.multienty.wechat.thirdparty.define.register.EnterpriseVerifyInfo;
import com.chia.multienty.wechat.thirdparty.parameter.register.BetaMiniProgramRegisterParameter;
import com.chia.multienty.wechat.thirdparty.parameter.register.EnterpriseMiniProgramRegisterParameter;
import com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo.AccountBasicInfoGetRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.platform.AuthorizationInfoGetByAuthCodeRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.register.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.AccountBasicInfoGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizationInfoGetByAuthCodeResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.*;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import com.chia.multienty.wechat.thirdparty.service.WxTPRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WxTPRegisterServiceImpl implements WxTPRegisterService {
    private final WxTPApiExecutor executor;
    private final YamlMultientyProperties properties;
    private final WechatAppService wechatAppService;
    private final WechatBetaMppRegisterAuditService wechatBetaMppRegisterAuditService;
    private final WechatBetaMppAuthAuditService wechatBetaMppAuthAuditService;
    private final WechatMppRegisterAuditService wechatMppRegisterAuditService;
    private final WechatMppNickNameAuditService wechatMppNickNameAuditService;
    private final ObjectMapper objectMapper;
    private final OAuthService oAuthService;
    @Value("${spring.profiles.active}")
    private String active;
    private final WxTPApiExecutor wxTPApiExecutor;

    @Override
    public BetaMiniProgramNickNameModifyResponse modifyBetaMiniProgramNickName(String appId, String name) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        BetaMiniProgramNickNameModifyRequest request = new BetaMiniProgramNickNameModifyRequest();
        request.setName(name);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    @SneakyThrows
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = { java.lang.Exception.class})
    public BetaMiniProgramRegisterResponse registerBetaMiniProgram(BetaMiniProgramRegisterParameter parameter) {
        BetaMiniProgramRegisterRequest request = new BetaMiniProgramRegisterRequest();
        request.setName(parameter.getName());
        WechatOAuthResult result = null;
        if(parameter.getCode() != null) {
            result = oAuthService.getAccessToken(parameter.getCode());
        } else {
            result = oAuthService.getAccessToken();
        }
        String pathPrefix = properties.getFile().getLocal().get("path-prefix");
        String uuid =  UUID.randomUUID().toString().replaceAll("-","");
        String finalPath = Paths.get(pathPrefix, "qrcode", uuid + ".jpg").toString();
        request.setOpenid(result.getOpenId());
        BetaMiniProgramRegisterResponse response = executor.execute(request);

        log.info("注册试用小程序结果:{}", objectMapper.writeValueAsString(response));
        QRCodeUtil.encode(response.getAuthorizeUrl(), null, finalPath, false);
        String qrcodeUrl = String.format("%s://%s/local/qrcode/%s",
                active.equals("dev") ? "http" : "https",
                properties.getDomain(),
                uuid + ".jpg"
        );
        response.setQrcodeUrl(qrcodeUrl);
        WechatApp app = new WechatApp();
        app.setUniqueId(response.getUniqueId());
        app.setTenantId(MultientyContext.getTenant().getTenantId());
        app.setStatus(StatusEnum.CREATED.getCode());
        app.setCreateMode(MppCreatingMode.REG_BETA_MPP.getValue().byteValue());
        wechatAppService.save(app);
        WechatBetaMppRegisterAudit audit = new WechatBetaMppRegisterAudit();
        audit.setName(parameter.getName());
        audit.setAuthorizeUrl(response.getAuthorizeUrl());
        audit.setErrorCode(response.getErrCode());
        audit.setErrorMsg(response.getErrMsg());
        audit.setOpenId(result.getOpenId());
        audit.setUnionId(result.getUnionId());
        audit.setStatus(StatusEnum.REVIEWING.getCode());
        audit.setTenantId(MultientyContext.getTenant().getTenantId());
        audit.setUniqueId(response.getUniqueId());
        wechatBetaMppRegisterAuditService.save(audit);
        return response;
    }

    @Override
    @SneakyThrows
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = { java.lang.Exception.class})
    public BetaMiniProgramVerifyResponse verifyBetaMiniProgram(String appId, EnterpriseVerifyInfo verifyInfo) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        BetaMiniProgramVerifyRequest request = new BetaMiniProgramVerifyRequest();
        request.setVerifyInfo(verifyInfo);
        request.setAccessToken(app.getAuthorizerAccessToken());
        WechatBetaMppAuthAudit audit = new WechatBetaMppAuthAudit();
        audit.setAppId(appId);
        audit.setLegalPersonaName(verifyInfo.getLegalPersonaName());
        audit.setComponentPhone(verifyInfo.getComponentPhone());
        audit.setCompanyCode(verifyInfo.getCode());
        audit.setLegalPersonaWechat(verifyInfo.getLegalPersonaWechat());
        audit.setParams(objectMapper.writeValueAsString(request));
        audit.setCompanyName(request.getVerifyInfo().getEnterpriseName());
        audit.setStatus(StatusEnum.VERIFYING.getCode());
        audit.setTenantId(app.getTenantId());
        wechatBetaMppAuthAuditService.save(audit);
        return executor.execute(request);
    }

    @Override
    public EnterpriseMiniProgramRegisterResponse registerEnterpriseMiniProgram(EnterpriseMiniProgramRegisterParameter parameter) {
        EnterpriseMiniProgramRegisterRequest request = new EnterpriseMiniProgramRegisterRequest();
        BeanUtils.copyProperties(parameter,request);
        WechatMppRegisterAudit audit = new WechatMppRegisterAudit();
        audit.setName(parameter.getName());
        audit.setCode(parameter.getCode());
        audit.setCodeType(parameter.getCodeType().getCode());
        audit.setComponentPhone(parameter.getComponentPhone());
        audit.setLegalPersonaName(parameter.getLegalPersonaName());
        audit.setLegalPersonaWechat(parameter.getLegalPersonaWechat());
        EnterpriseMiniProgramRegisterResponse response = executor.execute(request);
        audit.setTenantId(MultientyContext.getTenant().getTenantId());
        audit.setErrorCode(response.getErrCode());
        audit.setErrorMsg(response.getErrMsg());
        wechatMppRegisterAuditService.save(audit);
        return response;
    }

    /**
     * 在复用注册前需要商家使用公众号进行授权
     * @param appId
     * @param ticket
     * @return
     */
    @Override
    public MiniProgramRegisterByOfficialAccountResponse registerMiniProgramByOfficialAccount(String appId, String ticket) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        MiniProgramRegisterByOfficialAccountRequest request = new MiniProgramRegisterByOfficialAccountRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setTicket(ticket);
        MiniProgramRegisterByOfficialAccountResponse response = executor.execute(request);
        WechatApp wechatApp = new WechatApp();
        wechatApp.setCreateMode(MppCreatingMode.REG_FROM_OFFICIAL_ACC.getValue().byteValue());
        AuthorizationInfoGetByAuthCodeRequest authorRequest = new AuthorizationInfoGetByAuthCodeRequest();
        authorRequest.setAuthorizationCode(app.getAuthorizationCode());
        authorRequest.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        AuthorizationInfoGetByAuthCodeResponse authorResponse = wxTPApiExecutor.execute(authorRequest);
        wechatApp.setAuthorizerAccessToken(authorResponse.getAuthorizationInfo().getAuthorizerAccessToken());
        wechatApp.setAuthorizerRefreshToken(authorResponse.getAuthorizationInfo().getAuthorizerRefreshToken());
        wechatApp.setAuthorizerTokenTime(LocalDateTime.now());
        wechatApp.setAuthorizerTokenExpiresIn(authorResponse.getAuthorizationInfo().getExpiresIn());
        wechatApp.setStatus(StatusEnum.AUTHORIZED.getCode());

        AccountBasicInfoGetRequest accountBasicInfoGetRequest = new AccountBasicInfoGetRequest();
        accountBasicInfoGetRequest.setAccessToken(authorResponse.getAuthorizationInfo().getAuthorizerAccessToken());
        AccountBasicInfoGetResponse accountBasicInfoGetResponse = wxTPApiExecutor.execute(accountBasicInfoGetRequest);
        wechatApp.setAppNickName(accountBasicInfoGetResponse.getNickName());
        wechatApp.setAccountType(accountBasicInfoGetResponse.getAccountType().getValue().byteValue());
        wechatApp.setHeadImageUrl(accountBasicInfoGetResponse.getHeadImageInfo().getHeadImageUrl());
        wechatApp.setPrincipalName(accountBasicInfoGetResponse.getPrincipalName());
        wechatApp.setRealnameStatus(accountBasicInfoGetResponse.getRealNameStatus().getValue().byteValue());
        wechatApp.setQualificationVerify(accountBasicInfoGetResponse.getWxVerifyInfo().getQualificationVerify());

        wechatAppService.saveTE(wechatApp);
        return response;
    }
}
