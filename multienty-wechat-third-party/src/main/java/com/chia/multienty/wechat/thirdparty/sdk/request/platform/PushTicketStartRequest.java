package com.chia.multienty.wechat.thirdparty.sdk.request.platform;


import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.IWxRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.PushTicketStartResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 启动票据推送服务
 */
@Data
@WxApi(url = ThirdPlatformApis.Authorizing.START_PUSH_TICKET)
public class PushTicketStartRequest implements IWxRequest<PushTicketStartResponse> {
    /**
     * 平台型第三方平台的appid
     */
    @JsonProperty(value = "component_appid")
    private String componentAppId;

    /**
     * 平台型第三方平台的APPSECRET
     */
    @JsonProperty(value = "component_secret")
    private String componentSecret;
}
