package com.chia.multienty.plugin.core.metadata;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
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
@ApiModel(value = "PluginSource",description = "插件运行模式")
@Getter
@AllArgsConstructor
public enum PluginSource {
    MARKETING(1, "服务市场"),
    SELF(2, "自实现");
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
    public static PluginSource parse(Integer value) throws KutaRuntimeException {
        if(value == null) {
            return null;
        }
        for(PluginSource state: values()) {
            if(state.getValue().equals(value)) {
                return state;
            }
        }
        throw new KutaRuntimeException(HttpResultEnum.ENUM_ITEM_NOT_FOUND);
    }
}
