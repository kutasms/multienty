package com.chia.multienty.wechat.thirdparty.define.wxa;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信认证联系人信息
 */
public class WxAuthContactInfo {
    /**
     * 认证联系人姓名
     */
    @JsonProperty("name")
    private String name;
    /**
     * 认证联系人邮箱
     */
    @JsonProperty("email")
    private String email;
}
