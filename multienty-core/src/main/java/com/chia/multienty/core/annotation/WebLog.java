package com.chia.multienty.core.annotation;

import org.apache.logging.log4j.util.Strings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebLog {
    int type() default 1;
    String target() default Strings.EMPTY;
}
