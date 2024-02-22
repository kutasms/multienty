package com.chia.multienty.core.domain.enums;

import com.chia.multienty.core.exception.KutaRuntimeException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作类型
 */
@ApiModel(value = "OperationEnum",description = "操作类型")
@Getter
@AllArgsConstructor
public enum OperationEnum {
    SAVE(1, "新增"),
    UPDATE(2, "更新"),
    DELETE(3,"删除");
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
    public static OperationEnum parse(Integer value) throws KutaRuntimeException {
        if(value == null) {
            return null;
        }
        for(OperationEnum state: values()) {
            if(state.getValue().equals(value)) {
                return state;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
