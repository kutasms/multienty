package com.chia.multienty.core.parameter.base;

import com.chia.multienty.core.annotation.LogMetaId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
/**
 * <p>
 * 调度任务保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */

@Data
@ApiModel(value = "SchedulingJobSaveParameter",description = "调度任务保存请求")
@Accessors(chain = true)
public class SchedulingJobSaveParameter {

    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
    private Long jobId;
    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String jobName;
    /**
     * 类名称
     */
    @ApiModelProperty(value = "类名称")
    private String beanName;
    /**
     * 方法名称
     */
    @ApiModelProperty(value = "方法名称")
    private String methodName;
    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    private String params;
    /**
     * 参数类型列表
     */
    @ApiModelProperty(value = "参数类型列表")
    private String paramTypes;
    /**
     * cron表达式
     */
    @ApiModelProperty(value = "cron表达式")
    private String cronExp;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 运行状态
     */
    @ApiModelProperty(value = "运行状态")
    private Byte runningState;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
    /**
     * 任务类型
     */
    @ApiModelProperty(value = "任务类型")
    private Byte type;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startTime;
}
