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
@ApiModel(value = "SMSProviderType",description = "SMS提供者类型枚举")
public enum SMSProviderType {

    ALIYUN(1, "阿里云");


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
    public static SMSProviderType parse(Integer value) throws KutaRuntimeException {
        if(value == null) {
            return null;
        }
        for(SMSProviderType type : values()) {
            if(type.getValue().equals(value)) {
                return type;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
