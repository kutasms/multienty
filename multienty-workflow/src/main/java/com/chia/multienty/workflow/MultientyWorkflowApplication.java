package com.chia.multienty.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebFlux
public class MultientyWorkflowApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MultientyWorkflowApplication.class, args);
    }
}
