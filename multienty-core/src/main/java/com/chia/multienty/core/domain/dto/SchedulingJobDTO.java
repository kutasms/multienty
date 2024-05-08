package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.SchedulingJob;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 调度任务 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SchedulingJobDTO", description="调度任务DTO对象")
public class SchedulingJobDTO extends SchedulingJob {
}
