package com.chia.multienty.core.fusion.config;

import com.chia.multienty.core.dubbo.service.DubboMultientyService;
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
    public ReferenceBean<DubboMultientyService> dubboMultientyServiceReferenceBean() {
        return new ReferenceBean<>();
    }

    @Bean
    public DubboMultientyService dubboMultientyService(ReferenceBean<DubboMultientyService> dubboMultientyServiceReferenceBean) {
        DubboMultientyService dubboMultientyService = dubboMultientyServiceReferenceBean.getObject();
        return dubboMultientyService;
    }
}
