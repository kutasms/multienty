package com.chia.multienty.wechat.thirdparty.service;

import com.chia.multienty.wechat.thirdparty.sdk.response.platform.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.*;

public interface WxTPPlatformService {
    AuthorizationInfoGetByAuthCodeResponse getAuthorizationInfoByAuth(String authorizationCode);

    AuthorizerAccessTokenGetResponse getAuthorizerAccessToken(Long tenantId, String appId);

    AuthorizerInfoGetResponse getAuthorizerInfo(String appId);

    AuthorizerListGetResponse getAuthorizerList(Integer offset, Integer count);

    AuthorizerOptionInfoGetResponse getAuthorizerOptionInfo(String optionName);

    AuthorizerOptionInfoSetResponse setAuthorizerOptionInfo(String optionName, String optionValue);

    AuthorizerRefreshTokenGetResponse getAuthorizerRefreshToken(String authorizationCode);

    String getPreAuthCode();

    PushTicketStartResponse startPushTicket();
}
