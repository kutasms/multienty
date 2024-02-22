package com.chia.multienty.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RetryOnDistributedLockGetFailure {
    int maxRetry() default 10;
}
