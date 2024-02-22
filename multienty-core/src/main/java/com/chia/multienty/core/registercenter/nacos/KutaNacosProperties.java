package com.chia.multienty.core.registercenter.nacos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "spring.cloud.nacos.config", ignoreUnknownFields = true)
@Component
public class KutaNacosProperties {
    private String namespace;
    private String serverAddr;

    private String fileExtension;
    private String username;
    private String password;

    private NacosExtensionConfig[] extensionConfigs;
}
