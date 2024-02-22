package com.chia.multienty.spring.boot.registry;

import com.chia.multienty.core.domain.spi.TenantResourceMappingAlgorithm;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.env.Environment;

public class TenantResourceMappingAlgorithmBeanRegistry extends AbstractAlgorithmBeanRegistry<TenantResourceMappingAlgorithm> {

    private static final String PREFIX = "spring.kuta.multi-tenant.algorithms.resource-mapping.";

    public TenantResourceMappingAlgorithmBeanRegistry(final Environment environment) {
        super(environment);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        registerBean(PREFIX, TenantResourceMappingAlgorithm.class, registry);
    }
}
