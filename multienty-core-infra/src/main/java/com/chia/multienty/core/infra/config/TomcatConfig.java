package com.chia.multienty.core.infra.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatServerFactoryCustomizer() {
        return serverFactory -> {

            serverFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

                @Override
                public void customize(Connector connector) {
//                    connector.setMaxThreads(200);
//                    // 设置最小空闲线程数
//                    connector.setMinSpareThreads(20);
                }
            });
        };
    }
}
