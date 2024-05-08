package com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.LoginResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 小程序登录
 */
@Data
@WxApi(url = MerchantApis.BaseInfo.LOGIN, method = WxApi.Method.GET)
public class LoginRequest extends ComponentBaseRequest<LoginResponse> {
    /**
     * 小程序的 AppID
     */
    @JsonProperty(value = "appid")
    private String appId;
    /**
     * 填 authorization_code
     */
    @JsonProperty(value = "grant_type")
    private String grantType;
    /**
     * 第三方平台 appid
     */
    @JsonProperty(value = "component_appid")
    private String componentAppId;
    /**
     * wx.login 获取的 code
     */
    @JsonProperty(value = "js_code")
    private String jsCode;
}
