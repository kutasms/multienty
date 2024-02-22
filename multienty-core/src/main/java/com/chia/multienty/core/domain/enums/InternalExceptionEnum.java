package com.chia.multienty.core.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum InternalExceptionEnum {

    SERVICE_NOT_FOUND(1, "服务未找到异常");

    private Integer code;
    private String message;

    public static String parse(Integer code) {
        if(null == code) {
            return null;
        }
        InternalExceptionEnum httpResultEnum = Arrays.stream(InternalExceptionEnum.values()).filter(p -> p.getCode().equals(code)).findFirst().orElse(null);
        if(httpResultEnum != null) {
            return httpResultEnum.getMessage();
        }
        return null;
    }
}
