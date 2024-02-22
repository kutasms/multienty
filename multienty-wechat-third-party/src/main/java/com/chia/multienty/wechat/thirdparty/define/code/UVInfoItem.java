package com.chia.multienty.wechat.thirdparty.define.code;

import lombok.Data;

/**
 * 版本的用户占比列表
 */
@Data
public class UVInfoItem {
    /**
     * 基础库版本号
     */
    private String version;
    /**
     * 该版本用户占比
     */
    private Integer percentage;
}
