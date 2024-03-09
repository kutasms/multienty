package com.chia.multienty.core.config;

import com.chia.multienty.core.tools.MultientyPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebFluxConfiguration {

    /**
     * 密码编码
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MultientyPasswordEncoder();
    }

}
