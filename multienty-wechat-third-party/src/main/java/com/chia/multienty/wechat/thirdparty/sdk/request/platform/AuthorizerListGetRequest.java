package com.chia.multienty.wechat.thirdparty.sdk.request.platform;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizerListGetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 拉取已授权的账号列表请求
 */
@Data
@WxApi(url = ThirdPlatformApis.Authorizing.GET_AUTHORIZER_LIST)
public class AuthorizerListGetRequest extends ComponentBaseRequest<AuthorizerListGetResponse> {
    /**
     * 第三方平台 APPID
     */
    @JsonProperty(value = "component_appid")
    private String componentAppId;
    /**
     * 偏移位置/起始位置
     */
    @JsonProperty(value = "offset")
    private Integer offset;
    /**
     * 拉取数量，最大为 500
     */
    @JsonProperty(value = "count")
    private Integer count;
}
