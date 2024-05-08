package com.chia.multienty.wechat.thirdparty.define.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 	版本的用户占比列表
 */
@Data
public class UVInfo {
    /**
     * 	版本的用户占比列表
     */
    @JsonProperty("items")
    private List<UVInfoItem> items;
}
