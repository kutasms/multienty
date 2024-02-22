package com.chia.multienty.core.aspect;


import com.chia.multienty.core.annotation.RetryOnOptimisticLockUpdateFailure;
import com.chia.multienty.core.exception.OptimisticLockUpdateFailureException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;
@Aspect
@Component
@Slf4j
public class RetryOnOptimisticLockingFailureAspect {

    @Pointcut("@annotation(com.chia.multienty.core.annotation.RetryOnOptimisticLockUpdateFailure)")
    public void retryOnFailure() {

    }

    @Around("retryOnFailure()")
    @Transactional(rollbackFor = Exception.class)
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        int attempts = 0;
        RetryOnOptimisticLockUpdateFailure annotation = ((MethodSignature) pjp.getSignature())
                .getMethod().getAnnotation(RetryOnOptimisticLockUpdateFailure.class);
        do {
            attempts++;
            try {
                return pjp.proceed();
            }
            catch (OptimisticLockUpdateFailureException ex) {
                if(attempts > annotation.maxRetry()) {
                    //当前方案直接抛出异常,另有方案写入MQ队列等待下次执行
                    throw ex;
                } else {
                    TimeUnit.MILLISECONDS.sleep(50L);
                    //log failure information for audit/reference
                    //will try recovery
                    log.info("即将重新执行操作(分布式锁获取失败) {} 当期次数:{}", pjp.getTarget().toString(), attempts);
                }
            }
        } while (attempts <= annotation.maxRetry());
        return null;
    }
}
