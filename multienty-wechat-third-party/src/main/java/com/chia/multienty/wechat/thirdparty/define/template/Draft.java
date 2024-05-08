package com.chia.multienty.wechat.thirdparty.define.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 草稿
 */
@Data
public class Draft {
    /**
     * 开发者上传草稿时间戳
     */
    @JsonProperty("create_time")
    private Long createTime;
    /**
     * 版本号，开发者自定义字段
     */
    @JsonProperty("user_version")
    private String userVersion;
    /**
     * 版本描述 开发者自定义字段
     */
    @JsonProperty("user_desc")
    private String userDesc;
    /**
     * 草稿 id
     */
    @JsonProperty("draft_id")
    private Long draftId;
}
