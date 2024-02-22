package com.chia.multienty.core.domain.enums;

import com.chia.multienty.core.exception.KutaRuntimeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 终端类型枚举
 */
@AllArgsConstructor
@Getter
@ApiModel(value = "TerminalType",description = "终端类型枚举")
public enum TerminalType {

    MP_WECHAT(1, "微信小程序"),
    MP_ALIPAY(2,"支付宝小程序"),
    H5(3,"H5"),
    PC(4,"网页浏览器"),
    NATIVE_APP(5,"手机APP");


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
    public static TerminalType parse(Integer value) throws KutaRuntimeException {
        if(value == null) {
            return null;
        }
        for(TerminalType type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
