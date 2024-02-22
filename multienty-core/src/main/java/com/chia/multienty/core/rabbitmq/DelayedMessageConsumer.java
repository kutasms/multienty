package com.chia.multienty.core.rabbitmq;

import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.pojo.RabbitLog;
import com.chia.multienty.core.service.RabbitLogService;
import com.chia.multienty.core.rabbitmq.exception.KutaRabbitConsumingFailureException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.function.Consumer;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "spring.rabbitmq",name="enabled",havingValue = "true")
public class DelayedMessageConsumer {

    @Autowired
    private RabbitLogService rabbitLogService;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected StringRedisService stringRedisService;

    public void process(@NotNull String message,
                        @NotNull long deliveryTag,
                        @NotNull boolean idempotent,
                        @NotNull Channel channel,
                        @NotNull String dataTypeStr,
                        @NotNull Message msg,
                        @NotNull Consumer<String> consumer
                        ) {

        log.info("已收到消息: {},数据类型:{}", message, dataTypeStr);

        try {
            if(idempotent) {
                String key = msg.getMessageProperties().getHeaders().get("spring_returned_message_correlation").toString();
                //数据库方式处理幂等逻辑
                String cacheKey = String.format("%s-%s",RabbitConfig.IDEMPOTENT_CACHE_PREFIX, key);
                boolean locked = stringRedisService.lock(cacheKey, "1", 1000 * 60 * 30);
                if(!locked) {
                    log.info("此消息已被消费，跳出...");
                } else {
                    consumer.accept(message);
                }
                channel.basicAck(deliveryTag, false);
                stringRedisService.unlock(cacheKey, "1");
            } else {
                consumer.accept(message);
                channel.basicAck(deliveryTag, false);
            }
        }
        catch (Exception e) {
            try {
                channel.basicNack(
                        deliveryTag,
                        false,
                        false
                );
                handleException(msg, e);
            } catch (IOException ex) {
                log.error("Rabbit.channel.basicNack失败", ex);
            }
            throw new KutaRabbitConsumingFailureException("消费失败", e);
        }
    }

    private void handleException(Message msg,Throwable throwable) {
        String key = msg.getMessageProperties().getHeaders().get("spring_returned_message_correlation").toString();
        RabbitLog rabbitLog = rabbitLogService.getByKey(key);
        if(rabbitLog != null) {
            rabbitLog.setStatus(RabbitDeliveryStatus.EXCEPTION.name());
            rabbitLog.setUpdateTime(LocalDateTime.now());
            rabbitLog.setErrorMsg(throwable.getLocalizedMessage());
            rabbitLogService.updateById(rabbitLog);
        }
    }
}
