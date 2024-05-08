package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 调度任务详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "SchedulingJobDetailGetParameter",description = "调度任务详情获取请求")
public class SchedulingJobDetailGetParameter {
    /**
     * 任务编号
     */
     @ApiModelProperty(value = "任务编号")
     private Long jobId;
}
