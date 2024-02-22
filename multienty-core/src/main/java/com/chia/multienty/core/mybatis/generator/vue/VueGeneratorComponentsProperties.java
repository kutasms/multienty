package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;

import java.util.Map;

@Data
public class VueGeneratorComponentsProperties {

    private String path;

    private Map<String, VueGeneratorComponentProperties> mapping;

}
