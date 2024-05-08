package com.chia.multienty.core.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.pojo.RabbitLog;
import com.chia.multienty.core.service.RabbitLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Component
@Slf4j
public class ImmediateMessageProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Lazy
    private RetryManager retryManager;

    @Autowired
    private RabbitLogService rabbitLogService;

    @Autowired
    private StringRedisService stringRedisService;

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * 发送及时消息
     */
    public RabbitPushResult send(String id, String msg, String routingKey, RabbitDataType dataType,
                                 String boType, Long metaId) {
        return send(id, msg, routingKey, dataType, boType, metaId, false);
    }

    /**
     * 半开模式发送消息
     * <p>
     *     1. 先将消息保存至队列和数据库中，并提供一个consumer
     *     2. 执行consumer
     *     3. 如果consumer执行正常，则立即执行并将状态更新为committed，否则放弃该数据并将状态更新为rolled_back
     * </p>
     * @return
     */
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = {
            Exception.class})
    public RabbitPushResult sendHalf(String id, Object msg, String routingKey, RabbitDataType dataType,
                                     String boType, Long metaId, boolean idempotent, Consumer<RabbitLog> consumer) {
        CorrelationData data = new CorrelationData(id == null ? retryManager.generateId() : id);
        String json = null;
        try {
            json = objectMapper.writeValueAsString(msg);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        RabbitLog rabbitLog = new RabbitLog()
                .setMessage(json)
                .setKey(data.getId())
                .setDelayed(false)
                .setBoType(boType)
                .setDelayTime(0L)
                .setIdempotent(idempotent)
                .setDataType(dataType.name())
                .setRoutingKey(routingKey)
                .setHalfMode(true)
                .setMetaId(metaId);
        rabbitLog.setStatus(StatusEnum.HALF.getCode());

        // 将消息放入数据库中
        rabbitLogService.saveTE(rabbitLog);
        try {
            consumer.accept(rabbitLog);
        }
        catch (KutaRuntimeException ex) {
            log.error("半开模式发布消息时发生故障",ex);
            rabbitLog.setStatus(StatusEnum.ROLLED_BACK.getCode());
            rabbitLogService.updateByIdTE(rabbitLog);
            return new RabbitPushResult(false, ex, data.getId(), ex.getMessage(), ex.getCode());
        }

        rabbitLog.setStatus(StatusEnum.COMMITTED.getCode());
        rabbitLogService.updateByIdTE(rabbitLog);
        // 重试放入本地缓存
        retryManager.push(data.getId(), metaId, json, false,
                System.currentTimeMillis(),0, routingKey, null,
                dataType, boType, idempotent, false, true);

        this.rabbitTemplate.convertAndSend(
                RabbitConfig.IMMEDIATE_EXCHANGE,
                routingKey,
                msg,
                message -> {
                    message.getMessageProperties().getHeaders().put(KutaRabbitHeader.DATA_TYPE, dataType.name());
                    if(boType != null) {
                        message.getMessageProperties().getHeaders().put(KutaRabbitHeader.BO_TYPE, boType);
                    }
                    message.getMessageProperties().getHeaders().put(KutaRabbitHeader.IDEMPOTENT_FLAG, idempotent);
                    message.getMessageProperties().getHeaders().put(KutaRabbitHeader.KEY, data.getId());
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                }, data);

        return new RabbitPushResult(true, null, data.getId(), null, 0);
    }

    /**
     * 发送及时消息
     */
    public RabbitPushResult send(String id, String msg, String routingKey, RabbitDataType dataType,
                                 String boType, Long metaId, boolean idempotent) {
        CorrelationData data = new CorrelationData(id == null ? retryManager.generateId() : id);
        try {
            retryManager.push(data.getId(), metaId, msg, false,
                    System.currentTimeMillis(),0, routingKey, null,
                    dataType, boType, idempotent, false, false);
//            if(idempotent) {
//                /**
//                 * 幂等模式
//                 * 1.在redis中存入key
//                 * 2.消费时处理，判断是否为null
//                 *  2.1 如果结果为null，表示已经被消费，跳过
//                 *  2.2 如果不为null, 则消费并删除key缓存
//                 * */
//                String cacheKey = String.format("%s-%s",RabbitConfig.IDEMPOTENT_CACHE_PREFIX,data.getId());
//                stringRedisService.set(cacheKey, "1");
//            }

            this.rabbitTemplate.convertAndSend(
                    RabbitConfig.IMMEDIATE_EXCHANGE,
                    routingKey,
                    msg,
                    message -> {
                        message.getMessageProperties().getHeaders().put(KutaRabbitHeader.DATA_TYPE, dataType.name());
                        if(boType != null) {
                            message.getMessageProperties().getHeaders().put(KutaRabbitHeader.BO_TYPE, boType);
                        }
                        message.getMessageProperties().getHeaders().put(KutaRabbitHeader.IDEMPOTENT_FLAG, idempotent);
                        message.getMessageProperties().getHeaders().put(KutaRabbitHeader.KEY, data.getId());
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        return message;
                    }, data);
            return new RabbitPushResult(true, null, data.getId(), null, 0);
        }
        catch (KutaRuntimeException ex) {
            ex.printStackTrace();
            return new RabbitPushResult(false, ex, data.getId(), ex.getMessage(), ex.getCode());
        }
    }

    /**
     * 通过文本方式发送及时消息
     * @param id 消息编号
     * @param msg 消息内容
     * @param routingKey rabbit routing key
     * @return
     */
    public RabbitPushResult send(String id, String msg, String routingKey, Long metaId, boolean idempotent){
        return send(id, msg, routingKey, RabbitDataType.STRING, null, metaId, idempotent);
    }

    public RabbitPushResult send(String id, String msg, String routingKey, Long metaId) {
        return send(id, msg, routingKey, metaId, false);
    }

    /**
     * 通过fastjson发送及时消息
     * @param json 阿里巴巴JSONObject对象
     */
    public RabbitPushResult send(JSONObject json, String routingKey, String boType, Long metaId, boolean idempotent) {
       return send(null,json.toJSONString(),routingKey, RabbitDataType.FASTJSON, boType, metaId, idempotent);
    }

    public RabbitPushResult send(JSONObject json, String routingKey, String boType, Long metaId) {
        return send(json, routingKey, boType, metaId, false);
    }

    public RabbitPushResult send(JSONObject json, String routingKey, Long metaId, boolean idempotent){
        return send(json, routingKey, null, metaId, idempotent);
    }

    public RabbitPushResult send(JSONObject json, String routingKey, Long metaId) {
        return send(json, routingKey, metaId, false);
    }

    /**
     * 通过jackson发送及时消息
     * @param object Java对象
     * @throws JsonProcessingException Json转换异常
     */
    public RabbitPushResult send(Object object, String routingKey, String boType, Long metaId, boolean idempotent) {
        String id = retryManager.generateId();
        try {
            String msg = objectMapper.writeValueAsString(object);
            return send(id, msg, routingKey, RabbitDataType.JACKSON, boType, metaId, idempotent);
        }
        catch (JsonProcessingException ex) {
            return new RabbitPushResult(false, ex, id, ex.getMessage(), -1);
        }
    }

    public RabbitPushResult send(Object object, String routingKey, String boType, Long metaId) {
        return send(object, routingKey, boType, metaId, false);
    }

    public RabbitPushResult send(Object object, String routingKey, Long metaId){
        return send(object, routingKey, null, metaId);
    }

}
