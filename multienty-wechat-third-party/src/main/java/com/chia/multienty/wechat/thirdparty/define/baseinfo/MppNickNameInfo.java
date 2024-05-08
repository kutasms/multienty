package com.chia.multienty.wechat.thirdparty.define.baseinfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 名称信息
 */
@Data
public class MppNickNameInfo {
    /**
     * 小程序/公众号帐号名称
     */
    @JsonProperty("nickname")
    private String nickName;
    /**
     * 小程序名称已使用修改次数（本年）
     */
    @JsonProperty("modify_used_count")
    private Integer modifyUsedCount;
    /**
     * 小程序名称修改次数总额度（本年）
     */
    @JsonProperty("modify_quota")
    private Integer modifyQuota;
}
