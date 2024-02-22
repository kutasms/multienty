package com.chia.multienty.core.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.pojo.RabbitLog;
import com.chia.multienty.core.service.RabbitLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "spring.rabbitmq",name="enabled",havingValue = "true")
public class RabbitCallbackProcessor implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    @Autowired
    private DelayedMessageProducer delayedProducer;

    @Autowired
    private RetryManager retryManager;

    @Autowired
    private RabbitLogService rabbitLogService;

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        Message message = returnedMessage.getMessage();
        // 消息接收故障
        String msg = message.toString();

        String correlationId = message.getMessageProperties().getHeaders().get("spring_returned_message_correlation").toString();
        //记录日志
        log.info("RabbitMQ 消息接收故障 msg:{}, replyCode:{},replyText:{},exchange:{},routingKey:{}",
                message.toString().substring(0, msg.length() > 200 ? 200 : msg.length()),
                returnedMessage.getReplyCode(),
                returnedMessage.getReplyText(),
                returnedMessage.getExchange(),
                returnedMessage.getRoutingKey());

        if(returnedMessage.getExchange().equals(RabbitConfig.DELAYED_EXCHANGE)) {
            log.info("如果是延迟消息，则直接从重试队列中清除,id:{}", correlationId);
            retryManager.remove(correlationId);
            return;
        }
        RetryManager.UnverifiedMessage unverifiedMessage = retryManager.getMap()
                .get(correlationId);
        if(unverifiedMessage != null) {
            RabbitLog rabbitLog = new RabbitLog();
            rabbitLog.setRid(unverifiedMessage.getLogId());
            rabbitLog.setKey(correlationId);
            rabbitLog.setStatus(RabbitDeliveryStatus.PRODUCER_LOSE.name());
            rabbitLog.setMessage(unverifiedMessage.getMessage());
            rabbitLog.setMetaId(unverifiedMessage.getMetaId());
            rabbitLog.setBoType(unverifiedMessage.getBoType());
            rabbitLog.setDelayed(unverifiedMessage.getIsDelayed());
            rabbitLog.setDataType(unverifiedMessage.getDataType().name());
            rabbitLog.setTimestamp(unverifiedMessage.getTime());
            rabbitLog.setIdempotent(unverifiedMessage.getIdempotent());
            rabbitLog.setRoutingKey(returnedMessage.getRoutingKey());
            rabbitLog.setErrorMsg(msg.length() > 4500 ? msg.substring(0,4500): msg);
            rabbitLog.setSentTime(LocalDateTime.now());
            try {
                rabbitLogService.saveOrUpdate(rabbitLog);
            }catch (Exception ex) {
                log.error("RabbitMQ 消息接收回调保存日志失败", ex);
            }
        }
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("进入RabbitMQ发送回执, data: {}", JSONObject.toJSONString(correlationData));
        RetryManager.UnverifiedMessage unverifiedMessage = retryManager.getMap().get(correlationData.getId());
        if(!ack) {
            // 对数据库中已有数据做处理
            RabbitLog rabbitLog = rabbitLogService.getByKey(unverifiedMessage.getLogId().toString());
            if(rabbitLog != null && rabbitLog.getRid() !=null) {
                rabbitLog.setStatus(StatusEnum.FAILURE.getCode());
                rabbitLog.setErrorMsg(cause);
                rabbitLogService.updateByIdTE(rabbitLog);
            }

            //消息发送故障,重发
            log.error("RabbitMQ 消息推送故障 cause:{}, data:{}", cause, correlationData.toString());
            unverifiedMessage.setRetryStarted(true);
        } else {
            log.info("RabbitMQ 消息推送成功 id:{}", correlationData.getId());
            retryManager.remove(correlationData.getId());
        }
    }
}
