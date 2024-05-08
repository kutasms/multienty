package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import javax.validation.constraints.NotNull;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 调度任务删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */

@Data
@ApiModel(value = "SchedulingJobDeleteParameter",description = "调度任务删除请求")
@Accessors(chain = true)
public class SchedulingJobDeleteParameter {

    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    @LogMetaId
    private Long jobId;

}
