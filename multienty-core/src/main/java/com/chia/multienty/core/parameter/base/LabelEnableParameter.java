package com.chia.multienty.core.parameter.base;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 标签启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "LabelEnableParameter",description = "标签启用请求")
public class LabelEnableParameter {
    /**
     * 标签编号
     */
     @ApiModelProperty(value = "标签编号")
     private Long labelId;
}
