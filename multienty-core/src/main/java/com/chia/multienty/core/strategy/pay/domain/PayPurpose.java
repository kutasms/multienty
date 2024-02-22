package com.chia.multienty.core.strategy.pay.domain;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付目的枚举
 */
@Getter
@AllArgsConstructor
@ApiModel(value = "PayPurpose",description = "支付目的枚举")
public enum PayPurpose {

    ORDER(1, "订单支付"),
    RECHARGE(2, "账户充值"),
    DEPOSIT(3,"缴纳保证金"),
    TRADE(4, "交易支付"),
    BUY_MEMBER(5,"购买会员");

    /**
     * 值
     */
    @ApiModelProperty("值")
    @JsonValue
    private Integer value;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String describe;

    @JsonCreator
    public static PayPurpose parse(Integer code) throws KutaRuntimeException {
        if(code == null) {
            return null;
        }
        for(PayPurpose item : values()) {
            if(item.getValue().equals(code)) {
                return item;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ARGUMENT_ERROR);
    }
}
