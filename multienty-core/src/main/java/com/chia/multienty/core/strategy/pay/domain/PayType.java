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
 * 支付类型枚举
 * */
@Getter
@AllArgsConstructor
@ApiModel(value = "PayType",description = "支付类型枚举")
public enum PayType {
    BALANCE(1, "余额支付"),
    WECHAT_V3(2,"微信支付v3"),
    ALIPAY(3, "支付宝支付"),
    LAKALA(4,"拉卡拉支付"),
    WECHAT_V3_PARTNER(5, "微信支付服务商");


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
    public static PayType parse(Integer value) throws KutaRuntimeException {
        if(value == null) {
            return null;
        }
        for(PayType type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ARGUMENT_ERROR);
    }
}
