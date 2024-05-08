package com.chia.multienty.wechat.thirdparty.define.platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 授权信息
 */
@Data
public class SimpleAuthorizationInfo {
    /**
     * 已授权账号的 appid
     */
    @JsonProperty(value = "authorizer_appid")
    private String authorizerAppId;

    /**
     * 刷新令牌authorizer_refresh_token
     */
    @JsonProperty(value = "refresh_token")
    private String refreshToken;
    /**
     * 授权的时间
     */
    @JsonProperty(value = "auth_time")
    private String authTime;
}
