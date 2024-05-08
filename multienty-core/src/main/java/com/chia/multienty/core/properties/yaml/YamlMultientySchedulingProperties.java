package com.chia.multienty.core.properties.yaml;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.multienty.scheduling")
@Getter
@Setter
public class YamlMultientySchedulingProperties {
   private Boolean enabled = false;
}
