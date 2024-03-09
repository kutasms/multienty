package com.chia.multienty.core.fusion.sharding.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.shardingsphere.sharding.yaml.config.YamlShardingRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "spring.shardingsphere.rules")
@Getter
@Setter
@RefreshScope
public class KutaYamlShardingRuleSpringBootConfiguration{
    private YamlShardingRuleConfiguration sharding;
}
