package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;

import java.util.Map;

@Data
public class VueGeneratorViewsProperties {
    private Boolean enabled;
    private String path = "views";
    private Map<String, String> pathOverride;

    private Map<String, String> formatter;

    private Map<String, VueGeneratorViewPageProperties> pages;
}
