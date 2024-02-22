package com.chia.multienty.wechat.thirdparty.define.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MppMember {
    /**
     * 人员对应的唯一字符串
     */
    @JsonProperty(value = "userstr")
    private String userStr;
}
