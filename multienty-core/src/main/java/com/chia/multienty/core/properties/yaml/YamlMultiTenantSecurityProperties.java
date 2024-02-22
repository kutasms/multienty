package com.chia.multienty.core.properties.yaml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.kuta.multi-tenant.security")
public class YamlMultiTenantSecurityProperties {
    private YamlMultiTenantRsaProperties rsa;
    private YamlMultiTenantAuthProperties auth;
}
