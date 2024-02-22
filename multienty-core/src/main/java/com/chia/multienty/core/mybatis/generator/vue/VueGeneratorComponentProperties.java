package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;

@Data
public class VueGeneratorComponentProperties {
    /**
     * 类型 select, dialog, popover
     */
    private String type = "select";
    /**
     * 实体对象
     */
    private String entity;
    /**
     * 键
     */
    private String key;
    /**
     * 标签
     */
    private String label = "label";
    /**
     * 是否多选
     */
    private Boolean multiple = false;
    /**
     * 是否可检索
     */
    private Boolean searchable = false;
}
