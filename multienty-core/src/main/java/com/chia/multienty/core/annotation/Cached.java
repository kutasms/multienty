package com.chia.multienty.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识controller已缓存，此注释将被应用于缓存切面
 * <p>
 *     当请求参数命中缓存键时自动从缓存中读取数据并返回
 * </p>
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cached {
    public enum Type { LIST, STRING, HASHMAP }
    /**
     * 缓存超时时间
     * */
    long timeout() default 1000 * 60 * 10;
    /**
     * 缓存类型，默认使用字符串类型
     * */
    Type type() default Type.STRING;

}
