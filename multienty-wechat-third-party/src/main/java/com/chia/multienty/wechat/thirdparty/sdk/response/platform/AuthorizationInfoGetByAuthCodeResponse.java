package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import com.chia.multienty.wechat.thirdparty.define.platform.AuthorizationInfo;
import lombok.Data;

/**
 * 使用授权码获取授权信息响应数据
 */
@Data
public class AuthorizationInfoGetByAuthCodeResponse {
    /**
     * 授权信息
     */
    private AuthorizationInfo authorizationInfo;
}
