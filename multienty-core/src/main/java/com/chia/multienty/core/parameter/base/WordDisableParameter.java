package com.chia.multienty.core.parameter.base;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 关键词禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "WordDisableParameter",description = "关键词禁用请求")
public class WordDisableParameter {
    /**
     * 关键词编号
     */
     @ApiModelProperty(value = "关键词编号")
     private Long id;
}
