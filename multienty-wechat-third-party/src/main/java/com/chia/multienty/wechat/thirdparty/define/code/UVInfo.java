package com.chia.multienty.wechat.thirdparty.define.code;

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
    private List<UVInfoItem> items;
}
