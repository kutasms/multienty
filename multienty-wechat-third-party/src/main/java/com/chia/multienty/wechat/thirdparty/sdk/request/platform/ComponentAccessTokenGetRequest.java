package com.chia.multienty.wechat.thirdparty.sdk.request.platform;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.IWxRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.ComponentAccessTokenGetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取令牌
 */
@Data
@WxApi(url = ThirdPlatformApis.Authorizing.GET_COMPONENT_ACCESS_TOKEN)
public class ComponentAccessTokenGetRequest implements IWxRequest<ComponentAccessTokenGetResponse> {
    /**
     * 第三方平台 appid
     */
    @JsonProperty(value = "component_appid")
    private String componentAppId;
    /**
     * 第三方平台 appsecret
     */
    @JsonProperty(value = "component_appsecret")
    private String componentAppSecret;
    /**
     * 微信后台推送的 ticket
     */
    @JsonProperty(value = "component_verify_ticket")
    private String componentVerifyTicket;
}
