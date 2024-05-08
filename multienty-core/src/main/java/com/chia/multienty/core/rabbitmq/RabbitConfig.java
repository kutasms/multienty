package com.chia.multienty.core.rabbitmq;

import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    /**
     * 立即消费队列名称
     * */
    public static final String IMMEDIATE_QUEUE = "queue.immediate";

    public static final String IMMEDIATE_EXCHANGE = "amq.direct";
    public static final String DELAYED_EXCHANGE = "kuta.exchange.delayed";
    public static final String DELAYED_ROUTING_KEY = "routing.key.delay";

    public static final String IDEMPOTENT_CACHE_PREFIX = "RABBIT-IDEM";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue immediateQueue() {
        return new Queue(IMMEDIATE_QUEUE, true);
    }


    /**
     * 初始化连接工厂
     * @param addresses
     * @param userName
     * @param password
     * @param vhost
     * @return
     */
    @Bean
    ConnectionFactory connectionFactory(@Value("${spring.rabbitmq.host}:${spring.rabbitmq.port}") String addresses,
                                        @Value("${spring.rabbitmq.username}") String userName,
                                        @Value("${spring.rabbitmq.password}") String password,
                                        @Value("${spring.rabbitmq.virtual-host}") String vhost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(addresses);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vhost);
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


    @Bean
    public RabbitUtil rabbitUtil() {
        return new RabbitUtil();
    }


    @Bean(name="delayExchange")
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(
                DELAYED_EXCHANGE,
                "x-delayed-message",
                true,
                false,
                args);
    }

    @Bean(name="directExchange")
    public DirectExchange directExchange() {
        return new DirectExchange(
                IMMEDIATE_EXCHANGE,
                true,
                false);
    }

}
