package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取令牌
 */
@Data
public class ComponentAccessTokenGetResponse extends BaseResponse {
    /**
     * 第三方平台 access_token
     */
    @JsonProperty("component_access_token")
    private String componentAccessToken;
    /**
     * 有效期，单位：秒
     */
    @JsonProperty("expires_in")
    private Integer expiresIn;
}
