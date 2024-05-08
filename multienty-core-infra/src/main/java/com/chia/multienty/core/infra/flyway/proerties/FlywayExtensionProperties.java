package com.chia.multienty.core.infra.flyway.proerties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.flyway.extensions")
@Getter
@Setter
public class FlywayExtensionProperties {
    private String locationPrefix = "classpath:db/";

}
