package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取预授权码返回数据
 */
@Data
public class PreAuthCodeGetResponse extends BaseResponse {
    /**
     * 预授权码
     */
    @JsonProperty("pre_auth_code")
    private String preAuthCode;
    /**
     * 有效期，单位：秒
     */
    @JsonProperty("expires_in")
    private Integer expiresIn;
}
