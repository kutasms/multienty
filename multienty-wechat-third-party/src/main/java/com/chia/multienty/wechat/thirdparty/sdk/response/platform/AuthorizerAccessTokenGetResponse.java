package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import lombok.Data;

/**
 * 获取授权账号调用令牌请求
 */
@Data
public class AuthorizerAccessTokenGetResponse {
    /**
     * 	授权方令牌
     */
    private String authorizerAccessToken;
    /**
     * 第三方平台 appid
     */
    private String expiresIn;
    /**
     * 刷新令牌，获取授权信息时得到
     */
    private String authorizerRefreshToken;
}
