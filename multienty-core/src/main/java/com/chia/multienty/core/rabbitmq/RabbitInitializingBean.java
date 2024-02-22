package com.chia.multienty.core.rabbitmq;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "spring.rabbitmq",name="enabled",havingValue = "true")
@AllArgsConstructor
public class RabbitInitializingBean implements InitializingBean {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitCallbackProcessor rabbitCallbackProcessor;

    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback(rabbitCallbackProcessor);
        rabbitTemplate.setReturnsCallback(rabbitCallbackProcessor);
    }

//    @Bean
//    public RabbitTemplate rabbitTemplate(CachingConnectionFactory factory) {
//        factory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
//        factory.setPublisherReturns(true);
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
//        rabbitTemplate.setMandatory(true);
//        return rabbitTemplate;
//    }
}
