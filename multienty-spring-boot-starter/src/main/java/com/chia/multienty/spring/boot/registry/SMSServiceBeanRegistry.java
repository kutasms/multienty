package com.chia.multienty.spring.boot.registry;

import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.strategy.sms.SMSService;
import com.chia.multienty.core.tools.MultientyServiceLoader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Collection;

public class SMSServiceBeanRegistry implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        MultientyServiceLoader.register(SMSService.class);

        Collection<SMSService> services = MultientyServiceLoader.getInstances(SMSService.class);
        for (SMSService service : services) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(service.getClass());
            registry.registerBeanDefinition(MultientyConstants.SMS_SERVICE_BEAN_PREFIX + service.getType().name(), builder.getBeanDefinition());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
