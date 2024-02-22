package com.chia.multienty.core.parameter.base;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * Json配置禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "JsonConfigDisableParameter",description = "Json配置禁用请求")
public class JsonConfigDisableParameter {
    /**
     * 配置编号
     */
     @ApiModelProperty(value = "配置编号")
     private Long configId;
}
