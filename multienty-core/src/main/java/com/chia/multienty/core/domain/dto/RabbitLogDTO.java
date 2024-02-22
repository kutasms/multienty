package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.RabbitLog;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * Rabbit MQ日志信息 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="RabbitLogDTO", description="Rabbit MQ日志信息DTO对象")
public class RabbitLogDTO extends RabbitLog {
}
