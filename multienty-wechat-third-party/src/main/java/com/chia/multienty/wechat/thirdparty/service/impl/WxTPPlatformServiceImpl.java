package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.dubbo.service.DubboMultientyService;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.tools.IdWorkerProvider;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.wechat.scheduling.rabbitmq.domain.WechatBoType;
import com.chia.multienty.wechat.scheduling.rabbitmq.domain.bo.AuthorizerAccessTokenRefreshBO;
import com.chia.multienty.wechat.scheduling.rabbitmq.domain.constants.RabbitMqConstants;
import com.chia.multienty.wechat.thirdparty.define.baseinfo.MppCreatingMode;
import com.chia.multienty.wechat.thirdparty.sdk.request.platform.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.*;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPTokenProvider;
import com.chia.multienty.wechat.thirdparty.service.WxTPPlatformService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WxTPPlatformServiceImpl implements WxTPPlatformService {
    private final WxTPApiExecutor executor;
    private final YamlMultientyProperties properties;
    private final WechatAppService wechatAppService;
    private final WxTPTokenProvider tokenProvider;
    private final DubboMultientyService dubboMultientyService;
    private final StringRedisService stringRedisService;

    private final String PATTERN_AUTHORIZER_ACCESS_TOKEN_CACHE_KEY = "AUTH_ACC_TKN_%s_%s";
    private final String PATTERN_AUTH_LINK_GENERATE_URL = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=%s&pre_auth_code=%s&redirect_uri=%s&auth_type=%s";
    @Override
    public AuthorizationInfoGetByAuthCodeResponse getAuthorizationInfoByAuth(String authorizationCode) {
        AuthorizationInfoGetByAuthCodeRequest request = new AuthorizationInfoGetByAuthCodeRequest();
        request.setAuthorizationCode(authorizationCode);
        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        return executor.execute(request);
    }

    @Override
    @SneakyThrows
    public AuthorizerAccessTokenGetResponse getAuthorizerAccessToken(Long tenantId, Long programId) {
        String cacheKey = String.format(PATTERN_AUTHORIZER_ACCESS_TOKEN_CACHE_KEY,
                tenantId, programId);
        AuthorizerAccessTokenGetResponse response = stringRedisService.get(cacheKey,
                AuthorizerAccessTokenGetResponse.class);
        if(response == null) {
            WechatApp app = wechatAppService.getByIdAndSharding(new WechatApp()
                    .setProgramId(programId).setTenantId(tenantId));
            AuthorizerAccessTokenGetRequest request = new AuthorizerAccessTokenGetRequest();
            request.setComponentAccessToken(tokenProvider.getComponentAccessToken());
            request.setAuthorizerRefreshToken(app.getAuthorizerRefreshToken());
            request.setAuthorizerAppId(app.getMiniAppId());
            request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());

            response = executor.execute(request);
            stringRedisService.setJson(cacheKey, response, (2 * 60 * 60 - 20) * 1000);
        }
        return response;
    }

    @Override
    public void refreshAuthorizerAccessToken(AuthorizerAccessTokenRefreshBO refreshBO) {
        String cacheKey = String.format(PATTERN_AUTHORIZER_ACCESS_TOKEN_CACHE_KEY,
                refreshBO.getTenantId(), refreshBO.getAppId());

        if(!wechatAppService.isWorking(refreshBO.getTenantId(), refreshBO.getAppId())) {
            log.info("租户{}的应用{}未正常授权,跳过刷新授权令牌", refreshBO.getTenantId(), refreshBO.getAppId());
            return;
        }
        AuthorizerAccessTokenGetRequest request = new AuthorizerAccessTokenGetRequest();
        request.setComponentAccessToken(tokenProvider.getComponentAccessToken());
        request.setAuthorizerRefreshToken(refreshBO.getRefreshToken());
        request.setAuthorizerAppId(refreshBO.getAppId());
        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        AuthorizerAccessTokenGetResponse response = executor.execute(request);
        stringRedisService.setJson(cacheKey, response, (2 * 60 * 60 - 20) * 1000);
        dubboMultientyService.sendRabbitDelayMsg(
                new AuthorizerAccessTokenRefreshBO()
                        .setAppId(refreshBO.getAppId())
                        .setRefreshToken(response.getAuthorizerRefreshToken())
                        .setTenantId(refreshBO.getTenantId()),
                RabbitMqConstants.WECHAT_NOTIFY_ROUTING_KEY,
                (2 * 60 * 55) * 1000,
                WechatBoType.REFRESH_ACCESS_TOKEN.name(),
                IdWorkerProvider.next(),
                true
        );
    }

    @Override
    public AuthorizerInfoGetResponse getAuthorizerInfo(String appId) {
        AuthorizerInfoGetRequest request = new AuthorizerInfoGetRequest();
        request.setAuthorizerAppId(appId);
        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        return executor.execute(request);
    }

    @Override
    public AuthorizerListGetResponse getAuthorizerList(Integer offset, Integer count) {
        AuthorizerListGetRequest request = new AuthorizerListGetRequest();
        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        request.setCount(count);
        request.setOffset(offset);
        return executor.execute(request);
    }

    @Override
    public AuthorizerOptionInfoGetResponse getAuthorizerOptionInfo(String optionName) {
        AuthorizerOptionInfoGetRequest request = new AuthorizerOptionInfoGetRequest();
        request.setOptionName(optionName);
        return executor.execute(request);
    }

    @Override
    public AuthorizerOptionInfoSetResponse setAuthorizerOptionInfo(String optionName, String optionValue) {
        AuthorizerOptionInfoSetRequest request = new AuthorizerOptionInfoSetRequest();
        request.setOptionName(optionName);
        request.setOptionValue(optionValue);
        return executor.execute(request);
    }

    @Override
    public AuthorizerRefreshTokenGetResponse getAuthorizerRefreshToken(String authorizationCode) {
        AuthorizerRefreshTokenGetRequest request = new AuthorizerRefreshTokenGetRequest();
        request.setAuthorizationCode(authorizationCode);
        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        return executor.execute(request);
    }

    @Override
    public String getPreAuthCode() {
        return tokenProvider.getPreAuthCode();
    }

    @Override
    public PushTicketStartResponse startPushTicket() {
        PushTicketStartRequest request = new PushTicketStartRequest();
        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        request.setComponentSecret(properties.getWechat().getThirdParty().getAppSecret());
        return executor.execute(request);
    }

    @Override
    public AuthLinkGenerateResponse generateAuthLink(AuthLinkGenerateRequest request) {
        String preAuthCode = tokenProvider.getPreAuthCode();
        String url = String.format(PATTERN_AUTH_LINK_GENERATE_URL,
                properties.getWechat().getThirdParty().getAppId(),
                preAuthCode,
                request.getRedirectUrl(),
                request.getAuthType());

        WechatApp app = new WechatApp();
        app.setTenantId(MultientyContext.getTenant().getTenantId());
        app.setPreAuthCode(preAuthCode);
        app.setStatus(StatusEnum.WAITING.getCode());
        app.setCreateMode(MppCreatingMode.BIND_EXISTS_MPP.getValue().byteValue());
        wechatAppService.save(app);
        return new AuthLinkGenerateResponse().setUrl(url);
    }
}
