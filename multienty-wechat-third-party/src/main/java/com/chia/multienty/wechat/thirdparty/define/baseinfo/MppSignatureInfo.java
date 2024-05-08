package com.chia.multienty.wechat.thirdparty.define.baseinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 功能介绍信息
 */
@Data
public class MppSignatureInfo {
    @JsonProperty("signature")
    private String signature;
    @JsonProperty("modify_used_count")
    private Integer modifyUsedCount;
    @JsonProperty("modify_quota")
    private Integer modifyQuota;
}
