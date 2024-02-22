package com.chia.multienty.wechat.thirdparty.define.oa;

import lombok.Data;

import java.util.List;

/**
 * 关联的小程序列表
 */
@Data
public class LinkedMpp {
    private List<LinkedMppItem> items;
}
