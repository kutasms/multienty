package com.chia.multienty.core.parameter.log;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * RabbitMQ日志禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "RabbitLogDisableParameter",description = "RabbitMQ日志禁用请求")
public class RabbitLogDisableParameter {
    /**
     * 发送记录编号
     */
     @ApiModelProperty(value = "发送记录编号")
     private Long rid;
}
