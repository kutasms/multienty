package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import com.chia.multienty.wechat.thirdparty.define.platform.AuthorizationInfo;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取刷新令牌
 */
@Data
public class AuthorizerRefreshTokenGetResponse extends ComponentBaseRequest {
    /**
     * 授权信息
     */
    @JsonProperty("authorization_info")
    private AuthorizationInfo authorizationInfo;
}
