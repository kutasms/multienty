package com.chia.multienty.plugin.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class MultientyPluginWebApplication {
    public static void main(String[] args) {
        System.setProperty("dubbo.application.logger","slf4j");
        ConfigurableApplicationContext context = SpringApplication.run(MultientyPluginWebApplication.class, args);
    }
}