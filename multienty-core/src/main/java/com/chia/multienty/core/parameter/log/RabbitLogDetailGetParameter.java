package com.chia.multienty.core.parameter.log;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * Rabbit MQ日志信息详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "RabbitLogDetailGetParameter",description = "Rabbit MQ日志信息详情获取请求")
public class RabbitLogDetailGetParameter {
    /**
     * 发送记录编号
     */
     @ApiModelProperty(value = "发送记录编号")
     private Long rid;
}
