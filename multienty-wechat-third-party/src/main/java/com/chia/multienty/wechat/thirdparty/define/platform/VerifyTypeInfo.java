package com.chia.multienty.wechat.thirdparty.define.platform;

import lombok.Data;

/**
 * 公众号/小程序认证类型
 */
@Data
public class VerifyTypeInfo {
    /**
     * 类型id
     */
    private Integer id;
    /**
     * 类型说明
     */
    private String name;
}
