package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import com.chia.multienty.wechat.thirdparty.define.platform.AuthorizationInfo;
import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 使用授权码获取授权信息响应数据
 */
@Data
public class AuthorizationInfoGetByAuthCodeResponse extends BaseResponse {
    /**
     * 授权信息
     */
    @JsonProperty("authorization_info")
    private AuthorizationInfo authorizationInfo;
}
