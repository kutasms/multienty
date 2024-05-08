package com.chia.multienty.wechat.thirdparty.sdk.request.platform;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizerOptionInfoGetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取授权方选项信息
 */
@Data
@WxApi(url = ThirdPlatformApis.Authorizing.GET_AUTHORIZER_OPTION_INFO)
public class AuthorizerOptionInfoGetRequest extends ComponentBaseRequest<AuthorizerOptionInfoGetResponse> {
    /**
     * 选项名称
     */
    @JsonProperty("option_name")
    private String optionName;
}
