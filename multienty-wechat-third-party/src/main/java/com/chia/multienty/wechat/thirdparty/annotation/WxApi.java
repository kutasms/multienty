package com.chia.multienty.wechat.thirdparty.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 微信API
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WxApi {
    enum Method { GET, POST }

    String url() default "";

    Method method() default Method.POST;
    boolean postForm() default false;
}
