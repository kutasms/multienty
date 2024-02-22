package com.chia.multienty.wechat.thirdparty.define.code;

import lombok.Data;

/**
 * 体验版信息
 */
@Data
public class CodeExpInfo {
    /**
     * 提交体验版的时间
     */
    private Long expTime;
    /**
     * 体验版版本信息
     */
    private String expVersion;
    /**
     * 体验版版本描述
     */
    private String expDesc;
}
