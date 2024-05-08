package com.chia.multienty.wechat.thirdparty.sdk.request.platform;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizationInfoGetByAuthCodeResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 使用授权码获取授权信息
 */
@Data
@WxApi(url = ThirdPlatformApis.Authorizing.GET_AUTHORIZATION_INFO_BY_AUTH_CODE)
public class AuthorizationInfoGetByAuthCodeRequest extends ComponentBaseRequest<AuthorizationInfoGetByAuthCodeResponse> {
    /**
     * 第三方平台 appid
     */
    @JsonProperty(value = "component_appid")
    private String componentAppId;

    /**
     * 授权码, 会在授权成功时返回给第三方平台
     */
    @JsonProperty(value = "authorization_code")
    private String authorizationCode;
}
