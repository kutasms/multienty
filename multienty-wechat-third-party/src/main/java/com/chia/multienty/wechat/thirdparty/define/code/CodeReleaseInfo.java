package com.chia.multienty.wechat.thirdparty.define.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 	线上版信息
 */
@Data
public class CodeReleaseInfo {
    /**
     * 	发布线上版的时间
     */
    @JsonProperty("release_time")
    private Long releaseTime;
    /**
     * 线上版版本信息
     */
    @JsonProperty("release_version")
    private String releaseVersion;
    /**
     * 线上版本描述
     */
    @JsonProperty("release_desc")
    private String releaseDesc;
}
