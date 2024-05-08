package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import com.chia.multienty.wechat.thirdparty.define.platform.AuthorizationInfo;
import com.chia.multienty.wechat.thirdparty.define.platform.AuthorizerInfo;
import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 拉取已授权的账号信息响应数据
 */
@Data
public class AuthorizerInfoGetResponse extends BaseResponse {

    /**
     * 授权账号信息
     */
    @JsonProperty("component_appid")
    private AuthorizerInfo authorizerInfo;
    /**
     * 授权信息
     */
    @JsonProperty("authorizer_appid")
    private AuthorizationInfo authorizationInfo;
}
