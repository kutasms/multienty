package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;

import java.util.Map;

@Data
public class VueGeneratorComponentProperties {
    /**
     * 类型 SELECT, DIALOG, POPOVER
     */
    private String type = "SELECT";
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
    private String guideComponent = "el-button";
    /**
     * 是否多选
     */
    private Boolean multiple = false;
    /**
     * 是否可检索
     */
    private Boolean searchable = false;

    /**
     * 包名
     */
    private String path;

    /**
     * 绑定值类型
     */
    private String bindingValueType;
    /**
     * 绑定数据
     */
    private Map<String, Map<String, String>> bindings;
}
