package com.chia.multienty.wechat.thirdparty.define.template;

import lombok.Data;

/**
 * 草稿
 */
@Data
public class Draft {
    /**
     * 开发者上传草稿时间戳
     */
    private Long createTime;
    /**
     * 版本号，开发者自定义字段
     */
    private String userVersion;
    /**
     * 版本描述 开发者自定义字段
     */
    private String userDesc;
    /**
     * 草稿 id
     */
    private Long draftId;
}
