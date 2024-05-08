package com.chia.multienty.core.infra.registercenter.nacos;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
//@ConditionalOnProperty(prefix = "spring.cloud.nacos", name = "config")
public class NacosConfiguration {
    @Autowired
    private NacosConfigProperties properties;

    @Bean
    public ConfigService nacosConfigService() throws NacosException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, this.properties.getServerAddr());
        properties.put(PropertyKeyConst.NAMESPACE, this.properties.getNamespace());
        properties.put(PropertyKeyConst.USERNAME, this.properties.getUsername());
        properties.put(PropertyKeyConst.PASSWORD, this.properties.getPassword());
        ConfigService configService = NacosFactory.createConfigService(properties);
        return configService;
    }

}
