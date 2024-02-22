package com.chia.multienty.core.parameter.base;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 数据字典禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "DictDisableParameter",description = "数据字典禁用请求")
public class DictDisableParameter {
    /**
     * 字典编号
     */
     @ApiModelProperty(value = "字典编号")
     private Long dictId;
}
