package com.chia.multienty.core.mybatis.generator.vue;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "spring.vue-code-generator")
@Data
@Component
public class VueCodeGeneratorProperties {
    private Boolean enabled = true;
    private String projectPath;
    private Map<String, String> pathOverride;
    private List<String> ignoreEntities;
    /**
     * 文件重写映射
     */
    private Map<String, List<String>> overrideMapping;
    /**
     * 微服务模块映射
     */
    private Map<String, String> serviceModuleMapping;
    private VueGeneratorApiProperties apis;
    private VueGeneratorComponentsProperties components;
    private Map<String, String> inputIconMapping;

    private VueGeneratorViewsProperties views;
}
