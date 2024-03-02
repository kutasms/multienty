package com.chia.multienty.boot.admin.config;

import org.apache.catalina.valves.RemoteIpValve;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;


@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setUriEncoding(Charset.forName("UTF-8"));
        RemoteIpValve value = new RemoteIpValve();
        value.setRemoteIpHeader("X-Forwarded-For");
        value.setProtocolHeader("X-Forwarded-Proto");
        value.setProtocolHeaderHttpsValue("https");
        factory.addEngineValves(value);
        return factory;
    }
}
