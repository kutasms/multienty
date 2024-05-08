package com.chia.multienty.wechat.thirdparty.define.platform;

import lombok.Data;

/**
 * 基础配置信息
 */
@Data
public class MppBasicConfig {
    /**
     * 是否已经绑定手机号
     */
    private Boolean isPhoneConfigured;
    /**
     * 是否已经绑定邮箱，不绑定邮箱帐号的不可登录微信公众平台
     */
    private Boolean isEmailConfigured;
}
