package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import com.chia.multienty.core.annotation.LogMetaId;
/**
 * <p>
 * 调度任务启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */

@Data
@ApiModel(value = "SchedulingJobEnableParameter",description = "调度任务启用请求")
@Accessors(chain = true)
public class SchedulingJobEnableParameter {
    /**
     * 任务编号
     */
     @ApiModelProperty(value = "任务编号")
     @LogMetaId
     private Long jobId;
}
