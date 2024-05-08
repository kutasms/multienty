package com.chia.multienty.core.infra.aspect;

import com.chia.multienty.core.annotation.RetryOnDistributedLockGetFailure;
import com.chia.multienty.core.exception.DistributedLockGetException;
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
public class RetryOnDistributedLockGetFailureAspect {

    @Pointcut("@annotation(com.chia.multienty.core.annotation.RetryOnDistributedLockGetFailure)")
    public void retryOnFailure() {

    }

    @Around("retryOnFailure()")
    @Transactional(rollbackFor = Exception.class)
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        int attempts = 0;
        RetryOnDistributedLockGetFailure annotation = ((MethodSignature) pjp.getSignature())
                .getMethod().getAnnotation(RetryOnDistributedLockGetFailure.class);
        do {
            attempts++;
            try {
                return pjp.proceed();
            }
            catch (DistributedLockGetException ex) {
                if(attempts > annotation.maxRetry()) {
                    //当前方案直接抛出异常,另有方案写入MQ队列等待下次执行
                    throw ex;
                } else {
                    TimeUnit.MILLISECONDS.sleep(50L);
                    //log failure information for audit/reference
                    //will try recovery
                    log.info("即将重新执行操作(乐观锁) {} 当期次数:{}", pjp.getTarget().toString(), attempts);
                }
            }
        } while (attempts <= annotation.maxRetry());
        return null;
    }
}
