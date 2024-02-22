package com.chia.multienty.core.sharding.properties;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = DynamicDataSourceProperties.PREFIX)
public class ShardingSphereExtendProperties {
    private Boolean enabled = false;
}
