package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.domain.dto.SchedulingJobDTO;
import com.chia.multienty.core.mybatis.DefaultListGetParameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
/**
 * <p>
 * 调度任务分页列表查询请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */

@Data
@ApiModel(value = "SchedulingJobPageGetParameter",description = "调度任务分页列表查询请求")
@Accessors(chain = true)
public class SchedulingJobPageGetParameter extends DefaultListGetParameter<SchedulingJobDTO>{

    /**
     * 任务编号
     */
     @ApiModelProperty(value = "任务编号列表")
     private List<Long> jobIds;
    /**
     * 运行状态
     */
    @ApiModelProperty(value = "运行状态")
     private Byte runningState;
}
