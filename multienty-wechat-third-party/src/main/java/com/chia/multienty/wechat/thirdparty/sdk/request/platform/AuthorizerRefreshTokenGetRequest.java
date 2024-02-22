package com.chia.multienty.wechat.thirdparty.sdk.request.platform;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizerRefreshTokenGetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取刷新令牌
 */
@Data
@WxApi(url = ThirdPlatformApis.Authorizing.GET_AUTHORIZER_REFRESH_TOKEN)
public class AuthorizerRefreshTokenGetRequest extends ComponentBaseRequest<AuthorizerRefreshTokenGetResponse> {
    /**
     * 第三方平台 appid
     */
    @JsonProperty(value = "component_appid")
    private String componentAppId;
    /**
     * 授权码, 会在授权成功时返回给第三方平台，详见第三方平台授权流程说明。该参数也可以通过平台推送的"授权变更通知"获取
     */
    private String authorizationCode;
}
