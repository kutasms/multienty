package com.chia.multienty.wechat.thirdparty.define.baseinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 头像信息
 */
@Data
public class MppHeadImageInfo {
    /**
     * 头像 url
     */
    @JsonProperty("head_image_url")
    private String headImageUrl;
    /**
     * 头像已使用修改次数（本年）
     */
    @JsonProperty("modify_used_count")
    private Integer modifyUsedCount;
    /**
     * 头像修改次数总额度（本年）
     */
    @JsonProperty("modify_quota")
    private Integer modifyQuota;
}
