package com.chia.multienty.core.fusion.sharding.registry;

import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.env.Environment;

public class MTShardingAlgorithmProvidedBeanRegistry extends AbstractMTAlgorithmProvidedBeanRegistry<ShardingAlgorithm> {
    private static final String SHARDING_ALGORITHMS = "spring.shardingsphere.rules.sharding.sharding-algorithms.";

    public MTShardingAlgorithmProvidedBeanRegistry(Environment environment) {
        super(environment);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        registerBean(SHARDING_ALGORITHMS, ShardingAlgorithm.class, registry);
    }

    public void alterAlgorithm(BeanDefinitionRegistry registry) {
        alterBean(SHARDING_ALGORITHMS, ShardingAlgorithm.class, registry);
    }
}
