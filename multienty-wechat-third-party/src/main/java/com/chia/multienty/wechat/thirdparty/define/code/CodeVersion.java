package com.chia.multienty.wechat.thirdparty.define.code;

import lombok.Data;

/**
 * 模板信息列表。当action=get_history_version，才会返回
 */
@Data
public class CodeVersion {
    /**
     * 小程序版本
     */
    private Long appVersion;
    /**
     * 模板版本号，开发者自定义字段
     */
    private String userVersion;
    /**
     * 模板描述，开发者自定义字段
     */
    private String userDesc;
    /**
     * 更新时间，时间戳
     */
    private Long commitTime;
}
