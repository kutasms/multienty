package com.chia.multienty.plugin.core;

import com.chia.multienty.plugin.core.config.MultientyPluginConfiguration;
import com.chia.multienty.plugin.core.metadata.IMultientyPlugin;
import com.chia.multienty.plugin.core.properties.YamlMultientyPluginProperties;
import com.chia.multienty.plugin.core.register.MultientyImportBeanDefinitionRegister;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Map;

@EnableConfigurationProperties(YamlMultientyPluginProperties.class)
@Configuration
@Import(MultientyImportBeanDefinitionRegister.class)
public class MultientyPluginAutoConfiguration {

    @Autowired
    private YamlMultientyPluginProperties properties;

    @Bean
    public MultientyPluginConfiguration multientyPluginConfiguration(
            ObjectProvider<Map<String, IMultientyPlugin>> multientyPluginProvider
    ) {
        return MultientyPluginConfiguration.builder()
                .plugins(multientyPluginProvider.getIfAvailable())
                .build();
    }
}