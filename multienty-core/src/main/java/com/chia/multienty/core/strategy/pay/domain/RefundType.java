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
 * 退款类型枚举
 * */
@Getter
@AllArgsConstructor
@ApiModel(value = "RefundType",description = "退款类型枚举")
public enum RefundType {
    /**
     * 订单取消
     * */
    ORDER_CANCEL("订单取消",1),
    /**
     * 售后服务
     * */
    AFTER_SALE("售后服务",2),
    /**
     * 订单提前终止退款
     * */
    TERMINATING("订单提前终止退款", 3),
    /**
     * 订单转移退款
     * */
    TRANSFERRING("订单转移退款", 4);

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String describe;

    /**
     * 值
     */
    @ApiModelProperty("值")
    @JsonValue
    private Integer value;


    @JsonCreator
    public static RefundType parse(Integer value) throws KutaRuntimeException {
        if(value == null) {
            return null;
        }
        for(RefundType type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
