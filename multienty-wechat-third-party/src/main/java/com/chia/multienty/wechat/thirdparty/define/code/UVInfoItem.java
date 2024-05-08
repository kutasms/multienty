package com.chia.multienty.wechat.thirdparty.define.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 版本的用户占比列表
 */
@Data
public class UVInfoItem {
    /**
     * 基础库版本号
     */
    @JsonProperty("version")
    private String version;
    /**
     * 该版本用户占比
     */
    @JsonProperty("percentage")
    private Integer percentage;
}
