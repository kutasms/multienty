package com.chia.multienty.gateway;

import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebFlux
@ComponentScan(basePackages = {"com.chia.multienty.core","com.chia.multienty.gateway"})
@EnableConfigurationProperties(value = {YamlMultientyProperties.class})
@MapperScan(value={"com.chia.multienty.core.mapper"})
public class MultientyGatewayApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MultientyGatewayApplication.class);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(args);
    }
}
