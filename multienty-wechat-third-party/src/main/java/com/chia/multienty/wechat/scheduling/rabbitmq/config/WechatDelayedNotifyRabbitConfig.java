package com.chia.multienty.wechat.scheduling.rabbitmq.config;

import com.chia.multienty.wechat.scheduling.rabbitmq.domain.constants.RabbitMqConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * 订单延迟队列配置
 * */
@Configuration
@DependsOn(value="delayExchange")
public class WechatDelayedNotifyRabbitConfig {
    @Autowired
    public CustomExchange delayExchange;


    @Bean
    public Queue wechatDelayedNotifyQueue() {
        return new Queue(RabbitMqConstants.WECHAT_NOTIFY_QUEUE, true);
    }

    @Bean
    public Binding wechatDelayedBinding() {
        return BindingBuilder
                .bind(wechatDelayedNotifyQueue())
                .to(delayExchange)
                .with(RabbitMqConstants.WECHAT_NOTIFY_ROUTING_KEY).noargs();
    }
}
