package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;

/**
 * 联动设置，当page-type为LINKAGE时有效
 */
@Data
public class VueGeneratorLinkageProperties {
    /**
     * 联动字段
     */
    private String field;
    /**
     * 联动深度
     */
    private Integer depth;
    /**
     * 用于在子级中展示所属父级的标签的字段
     */
    private String labelField;
    private String boxWidth;
}
