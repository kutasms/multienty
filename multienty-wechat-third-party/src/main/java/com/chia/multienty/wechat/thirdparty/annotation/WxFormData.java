package com.chia.multienty.wechat.thirdparty.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 微信API
 * */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WxFormData {
    /**
     * 不超过{maxLength}M
     * @return
     */
    int maxLength() default 2;

    String alias() default "media";
}
