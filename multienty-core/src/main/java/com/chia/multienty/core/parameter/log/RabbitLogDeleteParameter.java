package com.chia.multienty.core.parameter.log;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * RabbitMQ日志删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "RabbitLogDeleteParameter",description = "RabbitMQ日志删除请求")
public class RabbitLogDeleteParameter {

    /**
     * 发送记录编号
     */
     @ApiModelProperty(value = "发送记录编号")
     @LogMetaId
     private Long rid;
}
