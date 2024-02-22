package com.chia.multienty.wechat.thirdparty.define.code;

import lombok.Data;

/**
 * 	线上版信息
 */
@Data
public class CodeReleaseInfo {
    /**
     * 	发布线上版的时间
     */
    private Long releaseTime;
    /**
     * 线上版版本信息
     */
    private String releaseVersion;
    /**
     * 线上版本描述
     */
    private String releaseDesc;
}
