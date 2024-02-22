package com.chia.multienty.core.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.RabbitLogDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.dubbo.service.DubboMultiTenantService;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.log.RabbitLogPageGetParameter;
import com.chia.multienty.core.pojo.RabbitLog;
import com.chia.multienty.core.service.RabbitLogService;
import com.chia.multienty.core.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableScheduling
@Configuration
@ConditionalOnProperty(prefix = "spring.rabbitmq",name="enabled",havingValue = "true")
@Async
@Slf4j
public class RetryExecutor {

    @Autowired
    private ImmediateMessageProducer immediateMessageProducer;
    @Autowired
    private DelayedMessageProducer delayedMessageProducer;
    private static boolean stopped = false;
    @Autowired
    private RabbitLogService rabbitLogService;

    @Autowired
    private RetryManager retryManager;

    @Value(value = "${spring.rabbitmq.retry-remove-threshold}")
    private Integer retryRemoveThreshold;
    @Value(value = "${spring.rabbitmq.resubmit-threshold}")
    private Integer resubmitThreshold;
    @Value(value = "${spring.rabbitmq.retry-running:}")
    private Boolean retryRunning;

    /**
     * 重新从数据库加载数据的时间间隔
     * */
    @Value(value = "${spring.rabbitmq.reload-from-db-interval}")
    private Long reloadFromDbInterval;

    public static void stop() {
        stopped = true;
    }

    @Autowired
    private DubboMultiTenantService dubboMultiTenantService;

    @Scheduled(cron = "${spring.rabbitmq.retry-execute-cron}")
    protected void retry() {
        if(retryRunning != null && retryRunning) {
            try{
                doRetry();
            }
            catch (Exception ex) {
                log.error("执行重新发送Rabbit信息时发生故障", ex);
            }
        } else {
            log.info("不执行重新发送Rabbit信息");
        }

    }

    @Scheduled(fixedDelayString = "${spring.rabbitmq.reload-from-db-interval}")
    protected void reloadFromDB() {
        if(retryRunning != null && retryRunning) {
            log.info("从数据库加载Rabbit数据");
            RabbitLogPageGetParameter parameter = new RabbitLogPageGetParameter();
            parameter.setStatusList(Arrays.asList(
                    RabbitDeliveryStatus.WAITING.name(),
                    RabbitDeliveryStatus.COMMITTED.name()));
            parameter.setPageSize(100);
            parameter.setCurrentPage(1);
            parameter.setTimestamp(System.currentTimeMillis() + reloadFromDbInterval);
            if(dubboMultiTenantService == null) {
                log.warn("多租户基础Dubbo服务无法连接...");
                return;
            }
            IPage<RabbitLogDTO> page = dubboMultiTenantService.getRabbitLogPage(parameter);

            if(page.getTotal() > 0) {
                page.getRecords().forEach(rabbitLog -> {
                    log.info("放入消息:{}", JSONObject.toJSONString(rabbitLog));
                    retryManager.getMap().put(rabbitLog.getKey(),
                            new RetryManager.UnverifiedMessage()
                                    .setBoType(rabbitLog.getBoType())
                                    .setDelayTime(rabbitLog.getDelayTime())
                                    .setMessage(rabbitLog.getMessage())
                                    .setTime(rabbitLog.getTimestamp())
                                    .setRoutingKey(rabbitLog.getRoutingKey())
                                    .setDeadline(TimeUtil.parseTimestamp(rabbitLog.getTimestamp()))
                                    .setIsDelayed(rabbitLog.getDelayed())
                                    .setLogId(rabbitLog.getRid())
                                    .setMetaId(rabbitLog.getMetaId())
                                    .setIdempotent(rabbitLog.getIdempotent())
                                    .setDataType(RabbitDataType.valueOf(rabbitLog.getDataType()))
                                    .setRetryStarted(true) //从数据库加载的数据要么是延迟数据，要么是发送失败的数据，全部要求立即重发
                    );
                });
            }

//            if(page.hasNext()) {
//                log.info("数据库中超过100条,继续加载Rabbit数据");
//                reloadFromDB();
//            }
        }
    }

    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,
            rollbackFor = {KutaRuntimeException.class, Exception.class})
    private void doRetry() {
        if(!stopped) {
            log.info("执行重新发送消息...");
            long now = System.currentTimeMillis();
            List<RabbitLog> updatingRabbitLogs = new ArrayList<>();
            for(String key: retryManager.getMap().keySet()) {
                log.info("key:{}", key);
                RetryManager.UnverifiedMessage msg = retryManager.getMap().get(key);
                if(null != msg && msg.getRetryStarted()) {
                    log.info("准备发送消息：{}", JSONObject.toJSONString(msg));
                    if(msg.getTime() + retryRemoveThreshold * resubmitThreshold < now) {
                        // 当超过N倍重新投送时间，则直接将该消息从本地缓存中移除
                        log.info("消息key:{}当超过N倍重新投送时间，直接将该消息从本地缓存中移除", key);
                        RabbitLog rabbitLog = rabbitLogService.getById(msg.getLogId());
                        rabbitLog.setUpdateTime(LocalDateTime.now());
                        rabbitLog.setStatus(StatusEnum.EXPIRED.getCode());
                        updatingRabbitLogs.add(rabbitLog);
                        retryManager.remove(key);
                    }
                    RabbitPushResult result = null;
                    if(msg.getIsDelayed()) {
                        result = delayedMessageProducer
                                .sendImmediately(key,msg.getMessage(),msg.getRoutingKey(),
                                        msg.getTime() - System.currentTimeMillis(),
                                        msg.getDataType(), msg.getBoType(), msg.getMetaId(), msg.getIdempotent());
                    } else {
                        result = immediateMessageProducer
                                .send(key, msg.getMessage(), msg.getRoutingKey(),
                                        msg.getDataType(), msg.getBoType(), msg.getMetaId(),msg.getIdempotent());
                    }

                    if(result.getSuccess()) {
                        log.info("发送成功，删除key:{}", key);
                        retryManager.remove(key);
                        if(msg.getLogId() != null) {
                            log.info("消息的LogId不为空, logId:{}", msg.getLogId());
                            RabbitLog rabbitLog = rabbitLogService.getById(msg.getLogId());
                            rabbitLog.setUpdateTime(LocalDateTime.now());
                            rabbitLog.setStatus(RabbitDeliveryStatus.SENT.name());
                            updatingRabbitLogs.add(rabbitLog);
                        }
                    } else {
                        /**
                         * 发送失败处理逻辑
                         */
                        if(result.getThrowable() != null) {
                            if(msg.getLogId()!=null) {
                                /**
                                 * 如果已保存至数据库则更新数据为发送失败
                                 */
                                RabbitLog rabbitLog = rabbitLogService.getById(msg.getLogId());
                                rabbitLog.setUpdateTime(LocalDateTime.now());
                                rabbitLog.setStatus(StatusEnum.FAILURE.getCode());
                                rabbitLog.setErrorMsg(result.getThrowable().getLocalizedMessage().length() > 5000
                                        ? result.getThrowable().getLocalizedMessage().substring(0, 4999)
                                        : result.getThrowable().getLocalizedMessage());
                                updatingRabbitLogs.add(rabbitLog);
                            }
                            else {
                                /**
                                 * 如果此消息从未保存至数据库，则将消息保存至数据库
                                 */
                                RabbitLog rabbitLog = new RabbitLog();
                                rabbitLog.setCreateTime(LocalDateTime.now());
                                rabbitLog.setKey(result.getKey());
                                rabbitLog.setVersion(1L);
                                rabbitLog.setRoutingKey(msg.getRoutingKey());
                                rabbitLog.setTimestamp(msg.getTime());
                                rabbitLog.setBoType(msg.getBoType());
                                rabbitLog.setDelayTime(msg.getDelayTime());
                                rabbitLog.setDataType(msg.getDataType().name());
                                rabbitLog.setStatus(RabbitDeliveryStatus.EXCEPTION.name());
                                rabbitLog.setErrorMsg(
                                        result.getThrowable().getLocalizedMessage().length() > 5000
                                                ? result.getThrowable().getLocalizedMessage().substring(0, 4999)
                                                : result.getThrowable().getLocalizedMessage());
                                rabbitLog.setMessage(msg.getMessage().length() > 5000
                                        ? msg.getMessage().substring(0, 4999) : msg.getMessage());
                                rabbitLog.setSentTime(LocalDateTime.now());
                                rabbitLogService.save(rabbitLog);
                            }
                            /**
                             * 此处不再从本地缓存中清理新缓存的键
                             * 在重试一定次数之后将自动删除
                             * 这里仅删除原始key
                             * */
                            retryManager.remove(key);
                        }
                    }
                }
            }
            if(updatingRabbitLogs.size() > 0) {
                log.info("需更新rabbit日志{}条", updatingRabbitLogs.size());
                if(!rabbitLogService.updateBatchById(updatingRabbitLogs)) {
                    log.error("RabbitMQ记录更新失败");
                }
            }
        }
    }
}
