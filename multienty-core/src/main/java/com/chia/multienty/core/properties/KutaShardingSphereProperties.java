package com.chia.multienty.core.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RefreshScope
@Getter
@ConfigurationProperties(prefix="spring.shardingsphere.datasource")
public class KutaShardingSphereProperties {

    private Map<String, Object> dataSource;
    private Map<String, Object> rules;
    private Map<String, Object> props;
}
