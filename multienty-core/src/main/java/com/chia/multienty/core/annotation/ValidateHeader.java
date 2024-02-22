package com.chia.multienty.core.annotation;


import com.chia.multienty.core.domain.constants.MultiTenantHeaderConstants;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateHeader {
    /**
     * 逗号分隔的header名称列表
     * @return
     */
    String headNames() default MultiTenantHeaderConstants.APP_ID_KEY;
}