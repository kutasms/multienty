package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;

@Data
public class VueGeneratorViewPageProperties {
    private Boolean override = false;
    private String tipsLabelField = "name";
    private VueGeneratorPageIndexProperties index;
    private VueGeneratorEditorProperties editor;
}
