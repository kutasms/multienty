package com.chia.multienty.wechat.thirdparty.define.cateory;

import lombok.Data;

/**
 * 已设置的类目
 */
@Data
public class MppSettingCategory {
    /**
     * 一级类目 ID
     */
    private Long first;
    /**
     * 一级类目名称
     */
    private String firstName;
    /**
     * 二级类目 ID
     */
    private Long second;
    /**
     * 二级类目名称
     */
    private String secondName;
    /**
     * 审核状态（1 审核中 2 审核不通过 3 审核通过）
     */
    private Integer auditStatus;
    /**
     * 审核不通过的原因
     */
    private String auditReason;
}
