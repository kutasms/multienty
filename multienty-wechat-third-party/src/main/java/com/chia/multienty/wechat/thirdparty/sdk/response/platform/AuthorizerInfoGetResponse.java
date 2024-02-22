package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import com.chia.multienty.wechat.thirdparty.define.platform.AuthorizationInfo;
import com.chia.multienty.wechat.thirdparty.define.platform.AuthorizerInfo;
import lombok.Data;

/**
 * 拉取已授权的账号信息响应数据
 */
@Data
public class AuthorizerInfoGetResponse {

    /**
     * 授权账号信息
     */
    private AuthorizerInfo authorizerInfo;
    /**
     * 授权信息
     */
    private AuthorizationInfo authorizationInfo;
}
