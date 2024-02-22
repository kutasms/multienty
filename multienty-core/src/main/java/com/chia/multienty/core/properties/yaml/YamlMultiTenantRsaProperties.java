package com.chia.multienty.core.properties.yaml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.kuta.multi-tenant.security.rsa")
public class YamlMultiTenantRsaProperties implements YamlProperties {
    private String privateKey;
    private String publicKey;
}
