package com.chia.multienty.wechat.thirdparty.define.baseinfo;

import lombok.Data;

/**
 * 头像信息
 */
@Data
public class MppHeadImageInfo {
    /**
     * 头像 url
     */
    private String headImageUrl;
    /**
     * 头像已使用修改次数（本年）
     */
    private Integer modifyUsedCount;
    /**
     * 头像修改次数总额度（本年）
     */
    private Integer modifyQuota;
}
