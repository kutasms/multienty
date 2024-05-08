package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取授权账号调用令牌请求
 */
@Data
public class AuthorizerAccessTokenGetResponse extends BaseResponse {
    /**
     * 	授权方令牌
     */
    @JsonProperty("authorizer_access_token")
    private String authorizerAccessToken;
    /**
     * 第三方平台 appid
     */
    @JsonProperty("expires_in")
    private String expiresIn;
    /**
     * 刷新令牌，获取授权信息时得到
     */
    @JsonProperty("authorizer_refresh_token")
    private String authorizerRefreshToken;
}
