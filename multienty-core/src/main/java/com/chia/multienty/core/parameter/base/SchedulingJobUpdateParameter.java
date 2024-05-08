package com.chia.multienty.core.parameter.base;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 调度任务更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */

@Data
@ApiModel(value = "SchedulingJobUpdateParameter",description = "调度任务更新请求")
@Accessors(chain = true)
public class SchedulingJobUpdateParameter {

    /**
     * 任务编号
     */
     @ApiModelProperty(value = "任务编号")
     @LogMetaId
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
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 乐观锁版本号
     */
     @ApiModelProperty(value = "乐观锁版本号")
     private Long version;
    /**
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
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
