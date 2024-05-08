package com.chia.multienty.wechat.thirdparty.sdk.request.platform;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizerInfoGetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 拉取已授权的账号信息请求
 */
@Data
@WxApi(url = ThirdPlatformApis.Authorizing.GET_AUTHORIZER_INFO)
public class AuthorizerInfoGetRequest extends ComponentBaseRequest<AuthorizerInfoGetResponse> {
    /**
     * 第三方平台 APPID
     */
    @JsonProperty(value = "component_appid")
    private String componentAppId;
    /**
     * 授权的公众号或者小程序的appid
     */
    @JsonProperty(value = "authorizer_appid")
    private String authorizerAppId;
}
