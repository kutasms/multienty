package com.chia.multienty.wechat.thirdparty.define.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 体验版信息
 */
@Data
public class CodeExpInfo {
    /**
     * 提交体验版的时间
     */
    @JsonProperty("exp_time")
    private Long expTime;
    /**
     * 体验版版本信息
     */
    @JsonProperty("exp_version")
    private String expVersion;
    /**
     * 体验版版本描述
     */
    @JsonProperty("exp_desc")
    private String expDesc;
}
