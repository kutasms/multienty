package com.chia.multienty.core.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.pojo.RabbitLog;
import com.chia.multienty.core.service.RabbitLogService;
import com.chia.multienty.core.util.TimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "spring.rabbitmq",name="enabled",havingValue = "true")
public class DelayedMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitLogService rabbitLogService;

    @Autowired
    @Lazy
    private RetryManager retryManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisService stringRedisService;

    /**
     * 重新从数据库加载数据的时间间隔
     * */
    @Value(value = "${spring.rabbitmq.reload-from-db-interval}")
    private Long reloadFromDbInterval;

    /**
     * 立即发送，不再将延迟消息缓存至数据库
     * @param id 消息id，long类型，推荐使用雪花算法
     * @param msg 消息
     * @param routingKey 路由键
     * @param delayTime 延迟时间
     * @param dataType 数据类型
     * @param boType 业务对象类型
     * @return
     */
    public RabbitPushResult sendImmediately(String id, String msg, String routingKey, Long delayTime,
                                            RabbitDataType dataType, String boType,Long metaId) {

        return sendImmediately(id, msg, routingKey, delayTime, dataType, boType, metaId, false);
    }
    public RabbitPushResult sendImmediately(String id, String msg, String routingKey, Long delayTime,
                                            RabbitDataType dataType, String boType,Long metaId,
                                            boolean idempotent) {
        CorrelationData data = new CorrelationData(id == null ? retryManager.generateId() : id);
        return this.sendImmediately(id, msg, routingKey, delayTime, dataType, boType, data, metaId, idempotent);
    }
    public RabbitPushResult sendImmediately(String id, String msg, String routingKey, Long delayTime,
                                            RabbitDataType dataType,
                                            String boType,
                                            CorrelationData data,
                                            Long metaId,
                                            boolean idempotent) {
        try {
            //计算过期时间
            LocalDateTime deadline = TimeUtil.parseTimestamp(System.currentTimeMillis() + delayTime);
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
            retryManager.push(data.getId(), metaId, msg, true, TimeUtil.toMills(deadline) ,delayTime, routingKey, deadline, dataType,
                    boType, idempotent, true, false);
            this.rabbitTemplate.convertAndSend(
                    RabbitConfig.DELAYED_EXCHANGE,
                    routingKey == null ? RabbitConfig.DELAYED_ROUTING_KEY : routingKey,
                    msg,
                    message -> {
                        message.getMessageProperties().getHeaders().put("x-delay", delayTime);
                        message.getMessageProperties().getHeaders().put(KutaRabbitHeader.DATA_TYPE, dataType.name());
                        if(boType != null) {
                            message.getMessageProperties().getHeaders().put(KutaRabbitHeader.BO_TYPE, boType);
                        }
                        message.getMessageProperties().getHeaders().put(KutaRabbitHeader.IDEMPOTENT_FLAG, idempotent);
                        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                        return message;
                    },data);
            return new RabbitPushResult(true, null, data.getId(), null, 0);
        }
        catch (Exception ex) {
            return new RabbitPushResult(false, ex, data.getId(), ex.getMessage(), -1);
        }
    }
    /**
     * 发送延时消息
     * @param msg 消息
     * @param delayTime 延迟时间
     */
    public RabbitPushResult send(String id,
                                 String msg,
                                 String routingKey,
                                 Long delayTime,
                                 RabbitDataType dataType,
                                 String boType,
                                 Long metaId,
                                 boolean idempotent) {
        CorrelationData data = new CorrelationData(id == null ? retryManager.generateId() : id);
        log.info("msg:{},delayTime:{},routingKey:{},key:{}",
                msg,
                delayTime,
                routingKey == null ? RabbitConfig.DELAYED_ROUTING_KEY: routingKey,
                data.getId());

        if(delayTime > reloadFromDbInterval) {
            // 当延迟时间大于每次从数据库加载RabbitMQ队列数据时间间隔时，直接放入数据库
            LocalDateTime now = LocalDateTime.now();
            RabbitLog log = new RabbitLog();
            log.setMessage(msg);
            log.setStatus(RabbitDeliveryStatus.WAITING.name());
            log.setKey(data.getId());
            log.setTimestamp(System.currentTimeMillis() + delayTime);
            log.setDelayTime(delayTime);
            log.setDataType(dataType.name());
            log.setErrorMsg(null);
            log.setRoutingKey(routingKey);
            log.setMetaId(metaId);
            log.setDelayed(true);
            log.setBoType(boType);
            log.setIdempotent(idempotent);
            rabbitLogService.save(log);
            return new RabbitPushResult().setKey(data.getId()).setSuccess(true).setThrowable(null);
        }
        return this.sendImmediately(id, msg, routingKey, delayTime, dataType, boType, data, metaId, idempotent);
    }

    public RabbitPushResult send(String id, String msg, String routingKey,
                                 long delayTime, String boType, Long metaId, boolean idempotent) {
        return send(id, msg, routingKey, delayTime, RabbitDataType.STRING, boType, metaId, idempotent);
    }

    public RabbitPushResult send(String id, String msg, String routingKey,
                                 long delayTime, Long metaId, boolean idempotent) {
        return send(id, msg, routingKey, delayTime, RabbitDataType.STRING, null, metaId, idempotent);
    }

    /**
     * 发送延时消息
     * @param json 阿里巴巴JSONObject对象
     * @param delayTime 延迟时间
     */
    public RabbitPushResult send(JSONObject json, String routingKey, long delayTime, Long metaId, boolean idempotent) {
        return send(null, json.toJSONString(), routingKey, delayTime,
                RabbitDataType.FASTJSON, null, metaId, idempotent);
    }

    public RabbitPushResult send(JSONObject json, String routingKey,
                                 long delayTime, String boType, Long metaId, boolean idempotent) {
        return send(null, json.toJSONString(), routingKey,
                delayTime, RabbitDataType.FASTJSON, boType, metaId, idempotent);
    }

    /**
     * 发送延时消息
     * @param object Java对象
     * @param delayTime 延迟时间
     * @throws JsonProcessingException Json转换异常
     */
    public RabbitPushResult send(Object object,String routingKey,
                                 long delayTime, String boType, Long metaId, boolean idempotent) {
        String id = retryManager.generateId();
        try {
            String msg = objectMapper.writeValueAsString(object);
            return send(id, msg, routingKey, delayTime, RabbitDataType.JACKSON, boType, metaId, idempotent);
        }catch (JsonProcessingException ex) {
            return new RabbitPushResult(false, ex, id, ex.getMessage(), -1);
        }
    }

    public RabbitPushResult send(Object object,String routingKey,
                                 long delayTime, String boType, Long metaId) {
        return send(object, routingKey, delayTime, boType, metaId, false);
    }

    public RabbitPushResult send(Object object,String routingKey, long delayTime, Long metaId, boolean idempotent) {
        return send(object, routingKey, delayTime,null, metaId, idempotent);
    }

    public RabbitPushResult send(Object object,String routingKey, long delayTime, Long metaId) {
        return send(object, routingKey, delayTime, metaId, false);
    }
}
