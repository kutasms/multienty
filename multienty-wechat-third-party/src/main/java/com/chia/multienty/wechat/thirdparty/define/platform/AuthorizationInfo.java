package com.chia.multienty.wechat.thirdparty.define.platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 授权信息
 */
@Data
public class AuthorizationInfo {
    /**
     * 授权的公众号或者小程序 appid
     */
    @JsonProperty(value = "authorizer_appid")
    private String authorizerAppId;

    /**
     * 接口调用令牌（在授权的公众号/小程序具备 API 权限时，才有此返回值）
     */
    @JsonProperty(value = "authorizer_access_token")
    private String authorizerAccessToken;

    /**
     * authorizer_access_token 的有效期（在授权的公众号/小程序具备API权限时，才有此返回值），单位：秒
     */
    @JsonProperty(value = "expires_in")
    private Integer expiresIn;
    /**
     * 刷新令牌（在授权的公众号具备API权限时，才有此返回值），
     * 刷新令牌主要用于第三方平台获取和刷新已授权用户的 authorizer_access_token。
     * 一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌。
     * 用户重新授权后，之前的刷新令牌会失效
     */
    @JsonProperty(value = "authorizer_refresh_token")
    private String authorizerRefreshToken;
    /**
     * 授权给第三方平台的权限集id列表，权限集id的含义可查看权限集介绍
     */
    @JsonProperty(value = "func_info")
    private List<FuncInfo> funcInfo;
}
