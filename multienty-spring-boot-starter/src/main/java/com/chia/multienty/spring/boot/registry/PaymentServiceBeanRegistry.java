package com.chia.multienty.spring.boot.registry;

import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.strategy.pay.PayService;
import com.chia.multienty.core.tools.MultientyServiceLoader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Collection;

public class PaymentServiceBeanRegistry implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        MultientyServiceLoader.register(PayService.class);

        Collection<PayService> services = MultientyServiceLoader.getInstances(PayService.class);
        for (PayService service : services) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(service.getClass());
            registry.registerBeanDefinition(
                    MultientyConstants.PAY_SERVICE_BEAN_PREFIX + service.getType(),
                    builder.getBeanDefinition());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
