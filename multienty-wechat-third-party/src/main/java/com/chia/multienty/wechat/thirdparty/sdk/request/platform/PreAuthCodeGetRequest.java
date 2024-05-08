package com.chia.multienty.wechat.thirdparty.sdk.request.platform;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.PreAuthCodeGetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取预授权码
 */
@Data
@WxApi(url = ThirdPlatformApis.Authorizing.GET_PRE_AUTH_CODE)
public class PreAuthCodeGetRequest extends ComponentBaseRequest<PreAuthCodeGetResponse> {
    /**
     * 第三方平台 appid
     */
    @JsonProperty(value = "component_appid")
    private String componentAppId;
}
