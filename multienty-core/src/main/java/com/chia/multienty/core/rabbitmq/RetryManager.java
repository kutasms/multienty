package com.chia.multienty.core.rabbitmq;

import com.chia.multienty.core.tools.SnowflakeIdWorker;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


@Slf4j
@Component
@ConditionalOnProperty(prefix = "spring.rabbitmq",name="enabled",havingValue = "true")
public class RetryManager {

    private Map<String, UnverifiedMessage> localCache = new ConcurrentHashMap<>();

    private AtomicLong id = new AtomicLong();

    @NoArgsConstructor
    @Data
    @Accessors(chain = true)
    public static class UnverifiedMessage {
        private Long time;
        private String message;
        private Boolean isDelayed;
        private Long delayTime;
        private String routingKey;
        private LocalDateTime deadline;
        private String boType;
        private Long logId;
        private Long metaId;
        private RabbitDataType dataType;
        @ApiModelProperty("是否启用幂等模式")
        private Boolean idempotent;
        @ApiModelProperty("是否开始重发")
        private Boolean retryStarted;
        @ApiModelProperty("是否半开模式")
        private Boolean halfMode;
    }

    public Map<String, UnverifiedMessage> getMap() {
        return localCache;
    }

    private SnowflakeIdWorker idWorker = new SnowflakeIdWorker(2,1);

    public String generateId() {
        return String.valueOf(idWorker.nextId());
    }

    public void remove(String id) {
        log.info("从本地缓存中清理Rabbit消息:{}", id);
        localCache.remove(id);
    }

    public void push(String id,
                     Long metaId,
                     String message,
                     boolean isDelayed,
                     Long timestamp,
                     long delayTime,
                     String routingKey,
                     LocalDateTime deadline,
                     RabbitDataType dataType,
                     String boType,
                     boolean idempotent,
                     boolean retryStarted,
                     boolean halfMode) {
        localCache.put(id,
            new UnverifiedMessage()
                    .setIsDelayed(isDelayed)
                    .setMetaId(metaId)
                    .setBoType(boType)
                    .setDataType(dataType)
                    .setDeadline(deadline)
                    .setTime(timestamp)
                    .setDelayTime(delayTime)
                    .setLogId(null)
                    .setMessage(message)
                    .setRoutingKey(routingKey)
                    .setIdempotent(idempotent)
                    .setRetryStarted(retryStarted)
                    .setHalfMode(halfMode)
        );
    }


}
