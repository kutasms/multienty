package com.chia.multienty.core.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.shardingsphere")
public class ShardingSphereExtendProperties {
    private Boolean enabled = false;
}
