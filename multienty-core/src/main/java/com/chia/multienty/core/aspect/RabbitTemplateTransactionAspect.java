package com.chia.multienty.core.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Aspect
@Order(50)
@Component
@Slf4j
public class RabbitTemplateTransactionAspect {

    /**
     * 代理convertAndSend方法够用
     */
    @Pointcut("execution(* org.springframework.amqp.rabbit.core.RabbitTemplate.convertAndSend(String, String, Object, org.springframework.amqp.core.MessagePostProcessor, org.springframework.amqp.rabbit.connection.CorrelationData))")
    public void convertAndSend() {
        // noop
    }
    @Around("convertAndSend()")
    public void aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (TransactionSynchronizationManager.isSynchronizationActive()
                && TransactionSynchronizationManager.isActualTransactionActive() // 事务开启判断
                && args.length == 5) {
            log.info("拦截RabbitTemplate发送：{}", args);
            // 注册同步器
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() { // 事务提交之后执行
                    try {
                        joinPoint.proceed();
                    } catch (Throwable throwable) {
                        throw new RuntimeException(throwable);
                    }
                }
            });
        } else { // 没有开启事务或者参数不正确就直接执行，不处理
            joinPoint.proceed();
        }
    }
}
