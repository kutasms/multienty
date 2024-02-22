package com.chia.multienty.wechat.thirdparty.define.baseinfo;

import lombok.Data;

/**
 * 功能介绍信息
 */
@Data
public class MppSignatureInfo {
    private String signature;
    private Integer modifyUsedCount;
    private Integer modifyQuota;
}
