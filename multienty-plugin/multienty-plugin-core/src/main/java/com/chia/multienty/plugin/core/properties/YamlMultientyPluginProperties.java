package com.chia.multienty.plugin.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.multienty.plugin")
public class YamlMultientyPluginProperties {
    private String classPath;
}
