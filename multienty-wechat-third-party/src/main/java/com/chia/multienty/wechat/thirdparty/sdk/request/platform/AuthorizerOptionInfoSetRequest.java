package com.chia.multienty.wechat.thirdparty.sdk.request.platform;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizerOptionInfoSetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 设置授权方选项信息
 */
@Data
@WxApi(url = ThirdPlatformApis.Authorizing.SET_AUTHORIZER_OPTION_INFO)
public class AuthorizerOptionInfoSetRequest extends ComponentBaseRequest<AuthorizerOptionInfoSetResponse> {
    /**
     * 选项名称
     */
    @JsonProperty("option_name")
    private String optionName;
    /**
     * 设置的选项值
     */
    @JsonProperty("option_value")
    private String optionValue;
}
