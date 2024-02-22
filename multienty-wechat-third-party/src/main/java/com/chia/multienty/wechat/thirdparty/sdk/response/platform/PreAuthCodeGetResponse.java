package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import lombok.Data;

/**
 * 获取预授权码返回数据
 */
@Data
public class PreAuthCodeGetResponse {
    /**
     * 预授权码
     */
    private String preAuthCode;
    /**
     * 有效期，单位：秒
     */
    private Integer expiresIn;
}
