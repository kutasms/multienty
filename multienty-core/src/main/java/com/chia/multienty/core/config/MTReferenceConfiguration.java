package com.chia.multienty.core.config;

import com.chia.multienty.core.dubbo.service.DubboMultiTenantService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.ReferenceBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "dubbo", name = "enabled", havingValue = "true")
public class MTReferenceConfiguration {
    @Bean
    @DubboReference(timeout = 5000)
    public ReferenceBean<DubboMultiTenantService> dubboMultiTenantServiceReferenceBean() {
        return new ReferenceBean<>();
    }

    @Bean
    public DubboMultiTenantService dubboMultiTenantService(ReferenceBean<DubboMultiTenantService> dubboMultiTenantServiceReferenceBean) {
        DubboMultiTenantService dubboMultiTenantService = dubboMultiTenantServiceReferenceBean.getObject();
        return dubboMultiTenantService;
    }
}
