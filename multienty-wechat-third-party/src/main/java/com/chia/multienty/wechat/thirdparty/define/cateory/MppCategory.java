package com.chia.multienty.wechat.thirdparty.define.cateory;

import lombok.Data;

import java.util.List;

/**
 * 类目信息
 */
@Data
public class MppCategory {
    /**
     * 类目ID
     */
    private Long id;
    /**
     * 类目名称
     */
    private String name;
    /**
     * 类目层级
     */
    private Integer level;
    /**
     * 类目父级 ID
     */
    private Long father;
    /**
     * 子级类目 ID
     */
    private List<Long> children;
    /**
     * 是否为敏感类目（1 为敏感类目，需要提供相应资质审核；0 为非敏感类目，无需审核）
     */
    private Integer sensitiveType;
    /**
     * sensitive_type 为 1 的类目需要提供的资质证明,通过qualify.exter_list.inner_list.name可查看资质名称。
     */
    private MppCategoryQualification qualify;
}
