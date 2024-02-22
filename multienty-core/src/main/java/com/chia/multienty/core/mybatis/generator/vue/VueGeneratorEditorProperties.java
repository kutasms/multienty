package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;

import java.util.Map;

@Data
public class VueGeneratorEditorProperties {
    private VueGeneratorEditorSizeProperties size = new VueGeneratorEditorSizeProperties();
    private String labelWidth = "100px";
    private Map<String, VueGeneratorEditorFormItemProperties> formItems;
}
