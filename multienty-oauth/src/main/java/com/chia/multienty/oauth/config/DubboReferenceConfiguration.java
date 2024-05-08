package com.chia.multienty.oauth.config;

import com.chia.multienty.core.dubbo.service.DubboWxOAuthService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.ReferenceBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "dubbo", name = "enabled", havingValue = "true")
public class DubboReferenceConfiguration {
    @Bean
    @DubboReference(timeout = 5000)
    public ReferenceBean<DubboWxOAuthService> dubboWxOAuthServiceReferenceBean() {
        return new ReferenceBean<>();
    }

    @Bean
    public DubboWxOAuthService dubboWxOAuthService(ReferenceBean<DubboWxOAuthService> dubboWxOAuthServiceReferenceBean) {
        DubboWxOAuthService dubboWxOAuthService = dubboWxOAuthServiceReferenceBean.getObject();
        return dubboWxOAuthService;
    }
}
