package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.wechat.thirdparty.sdk.request.platform.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.*;
import com.chia.multienty.wechat.thirdparty.service.WxTPPlatformService;
import com.chia.multienty.wechat.thirdparty.sdk.request.platform.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.*;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPTokenProvider;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WxTPPlatformServiceImpl implements WxTPPlatformService {
    private final WxTPApiExecutor executor;
    private final YamlMultiTenantProperties properties;
    private final WechatAppService wechatAppService;
    private final WxTPTokenProvider tokenProvider;
    @Override
    public AuthorizationInfoGetByAuthCodeResponse getAuthorizationInfoByAuth(String authorizationCode) {
        AuthorizationInfoGetByAuthCodeRequest request = new AuthorizationInfoGetByAuthCodeRequest();
        request.setAuthorizationCode(authorizationCode);
        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        return executor.execute(request);
    }

    @Override
    public AuthorizerAccessTokenGetResponse getAuthorizerAccessToken(Long tenantId, String appId) {
        WechatApp app = wechatAppService.getBy(tenantId, appId);
        AuthorizerAccessTokenGetRequest request = new AuthorizerAccessTokenGetRequest();
        request.setComponentAccessToken(tokenProvider.getComponentAccessToken());
        request.setAuthorizerRefreshToken(app.getAuthorizerRefreshToken());
        request.setAuthorizerAppId(appId);
        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        return executor.execute(request);
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
}
