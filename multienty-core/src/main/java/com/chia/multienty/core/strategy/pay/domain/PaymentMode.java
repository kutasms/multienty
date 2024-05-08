package com.chia.multienty.core.strategy.pay.domain;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 客户提交的支付方式
 */
@Getter
@AllArgsConstructor
public enum PaymentMode {
    BALANCE(1, "余额支付"),
    INTEGRAL(2, "积分支付"),
    WECHAT(3, "微信支付"),
    ALIPAY(4, "支付宝支付")
    ;
    @JsonValue
    private Integer value;
    private String describe;

    @JsonCreator
    public static PaymentMode valueOf(Integer value) throws KutaRuntimeException {
        if(value == null) {
            return null;
        }
        for(PaymentMode type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ARGUMENT_ERROR);
    }
}
