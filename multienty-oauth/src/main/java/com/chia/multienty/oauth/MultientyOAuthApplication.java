package com.chia.multienty.oauth;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = {
        "com.chia.multienty.core",
        "com.chia.multienty.oauth",
})
@EnableDubbo
@MapperScan(value={"com.chia.multienty.core.mapper"})
public class MultientyOAuthApplication {
    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger","slf4j");
        SpringApplication application = new SpringApplication(MultientyOAuthApplication.class);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(args);
//        ConfigurableApplicationContext context = SpringApplication.run(MultientyOAuthApplication.class, args);
    }
}
