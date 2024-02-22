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
public class ImmediateMessageConsumer {
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

        log.info("已收到直连交换机消息: {},数据类型:{}", message, dataTypeStr);

        try {
            String key = msg.getMessageProperties().getHeaders().get("spring_returned_message_correlation").toString();
            if(idempotent) {
                //缓存方式处理幂等逻辑
                String cacheKey = String.format("%s-%s",RabbitConfig.IDEMPOTENT_CACHE_PREFIX, key);
                boolean locked = stringRedisService.lock(cacheKey, "1", 1000 * 60 * 30);
                if(!locked) {
                    log.info("此消息已被消费，跳出...");
                } else {
                    log.info("[Rabbit MQ]消费幂等消息{}...", key);
                    consumer.accept(message);
                    stringRedisService.unlock(cacheKey, "1");
                }
                channel.basicAck(deliveryTag, false);
            } else {
                consumer.accept(message);
                channel.basicAck(deliveryTag, false);
            }
            rabbitLogService.finish(key);
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
        try {
            String key = msg.getMessageProperties().getHeaders().get("spring_returned_message_correlation").toString();
            RabbitLog rabbitLog = rabbitLogService.getByKey(key);
            if (rabbitLog != null) {
                rabbitLog.setStatus(RabbitDeliveryStatus.EXCEPTION.name());
                rabbitLog.setUpdateTime(LocalDateTime.now());
                rabbitLog.setErrorMsg(throwable.getLocalizedMessage());
                rabbitLogService.updateById(rabbitLog);
            }
        }catch (Exception ex) {
            log.error("更新Rabbit日志发生故障", ex);
        }
    }
}
