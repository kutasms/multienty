package com.chia.multienty.core.strategy.pay.domain;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微信支付类型
 * */
@Getter
@AllArgsConstructor
public enum PaySource {
    H5(1,"H5"),
    MINI_PROGRAM(2,"小程序"),
    NATIVE_APP(3, "手机APP");

    @JsonValue
    private Integer value;
    private String name;

    @JsonCreator
    public static PaySource parse(Integer value) throws KutaRuntimeException {
        if(value == null) {
            return null;
        }
        for(PaySource type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ARGUMENT_ERROR);
    }
}
