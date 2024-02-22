package com.chia.multienty.core.parameter.base;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 标签禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "LabelDisableParameter",description = "标签禁用请求")
public class LabelDisableParameter {
    /**
     * 标签编号
     */
     @ApiModelProperty(value = "标签编号")
     private Long labelId;
}
