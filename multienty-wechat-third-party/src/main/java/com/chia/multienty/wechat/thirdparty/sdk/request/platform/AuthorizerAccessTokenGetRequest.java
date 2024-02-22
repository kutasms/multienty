package com.chia.multienty.wechat.thirdparty.sdk.request.platform;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.IWxRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizerAccessTokenGetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取授权账号调用令牌请求
 */
@Data
@WxApi(url = ThirdPlatformApis.Authorizing.GET_AUTHORIZER_ACCESS_TOKEN)
public class AuthorizerAccessTokenGetRequest implements IWxRequest<AuthorizerAccessTokenGetResponse> {
    /**
     * 第三方平台接口调用凭证component_access_token，该参数为 URL 参数，非 Body 参数。
     */
    private String componentAccessToken;
    /**
     * 第三方平台 appid
     */
    @JsonProperty(value = "component_appid")
    private String componentAppId;
    /**
     * 授权方 appid
     */
    @JsonProperty(value = "authorizer_appid")
    private String authorizerAppId;
    /**
     * 刷新令牌，获取授权信息时得到
     */
    private String authorizerRefreshToken;
}
