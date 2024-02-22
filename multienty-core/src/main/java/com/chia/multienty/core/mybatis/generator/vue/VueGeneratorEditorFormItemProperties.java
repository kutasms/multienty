package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class VueGeneratorEditorFormItemProperties {
    private String component = "el-input";

    private String name;
    private String path;
    private String type;

    private Boolean readOnly = false;
    private Map<String, String> bindings;
    private Map<String, String> options;
    
}
