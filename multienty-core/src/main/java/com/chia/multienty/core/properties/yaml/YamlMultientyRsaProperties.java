package com.chia.multienty.core.properties.yaml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.multienty.security.rsa")
public class YamlMultientyRsaProperties implements YamlProperties {
    private String privateKey;
    private String publicKey;
}
