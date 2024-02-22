package com.chia.multienty.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
@Slf4j
public class AsyncConfig implements AsyncConfigurer {
    //注册执行器
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(80);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.initialize();//如果不初始化，导致找到不到执行器
        return taskExecutor;
    }
    // 用于捕获异步异常
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        return null;
    }
}
