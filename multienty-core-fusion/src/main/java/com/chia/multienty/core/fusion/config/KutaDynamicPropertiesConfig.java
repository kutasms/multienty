package com.chia.multienty.core.fusion.config;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.chia.multienty.core.properties.KutaDynamicDataSourceProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@RefreshScope
@Configuration(proxyBeanMethods = false)
public class KutaDynamicPropertiesConfig {
    @RefreshScope
    @Bean("dynamicDataSourceProperties")
    @Primary
    @ConditionalOnMissingBean
    public DynamicDataSourceProperties dynamicDataSourceProperties() {
        return new KutaDynamicDataSourceProperties();
    }
}
