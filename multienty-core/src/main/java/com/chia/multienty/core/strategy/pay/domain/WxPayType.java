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
public enum WxPayType {
    H5(1,"手机网页"),
    MINI_APP(2,"小程序"),
    APP(3, "手机APP");

    @JsonValue
    private Integer value;
    private String name;

    @JsonCreator
    public static WxPayType parse(Integer value) throws KutaRuntimeException {
        if(value == null) {
            return null;
        }
        for(WxPayType type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ARGUMENT_ERROR);
    }
}
