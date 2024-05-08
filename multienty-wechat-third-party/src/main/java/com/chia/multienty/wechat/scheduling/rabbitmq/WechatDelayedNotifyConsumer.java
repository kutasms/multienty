package com.chia.multienty.wechat.scheduling.rabbitmq;

import com.chia.multienty.core.rabbitmq.DelayedMessageConsumer;
import com.chia.multienty.core.rabbitmq.KutaRabbitHeader;
import com.chia.multienty.core.rabbitmq.RabbitDataType;
import com.chia.multienty.core.rabbitmq.exception.KutaRabbitConsumingFailureException;
import com.chia.multienty.wechat.scheduling.rabbitmq.domain.WechatBoType;
import com.chia.multienty.wechat.scheduling.rabbitmq.domain.bo.AuthorizerAccessTokenRefreshBO;
import com.chia.multienty.wechat.scheduling.rabbitmq.domain.constants.RabbitMqConstants;
import com.chia.multienty.wechat.thirdparty.service.WxTPPlatformService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Header;

@EnableRabbit
@Configuration
@Slf4j
public class WechatDelayedNotifyConsumer extends DelayedMessageConsumer {
    @Autowired
    private WxTPPlatformService wxTPPlatformService;
    @RabbitListener(queues = RabbitMqConstants.WECHAT_NOTIFY_QUEUE,
            concurrency = "${spring.rabbitmq.listener.simple.concurrency}")
    public void message(String message,
                        @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                        @Header(KutaRabbitHeader.IDEMPOTENT_FLAG) Boolean idempotent,
                        @Header(KutaRabbitHeader.DATA_TYPE) String dataTypeStr,
                        @Header(KutaRabbitHeader.BO_TYPE) String boTypeStr,
                        Channel channel,
                        Message msg) {
        super.process(message, deliveryTag, idempotent, channel, dataTypeStr, msg, data -> {
            log.info("[微信]延迟消息: boType:{}, msg:{}", boTypeStr, message);
            RabbitDataType dataType = RabbitDataType.valueOf(dataTypeStr);
            switch (dataType) {
                case JACKSON:
                    WechatBoType boType = WechatBoType.valueOf(boTypeStr);
                    switch (boType) {
                        case REFRESH_ACCESS_TOKEN:
                            try {
                                AuthorizerAccessTokenRefreshBO refreshBO = objectMapper.readValue(message,
                                        AuthorizerAccessTokenRefreshBO.class);
                                wxTPPlatformService.refreshAuthorizerAccessToken(refreshBO);
                            } catch (JsonProcessingException e) {
                                log.error("微信通知消费失败,JACKSON转换失败,message:{}", message);
                                throw new KutaRabbitConsumingFailureException(e);
                            }
                            break;
                    }
                    break;
            }
        });
    }
}
