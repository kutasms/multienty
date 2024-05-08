package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 调度任务
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_scheduling_job")
@ApiModel(value="SchedulingJob对象", description="调度任务")
public class SchedulingJob extends KutaBaseEntity {


    /**
     * 任务编号
     */
    @ApiModelProperty(value = "任务编号")
    @TableId(value = "job_id", type = IdType.INPUT)
    private Long jobId;

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    @TableField("`job_name`")
    private String jobName;

    /**
     * 类名称
     */
    @ApiModelProperty(value = "类名称")
    @TableField("`bean_name`")
    private String beanName;

    /**
     * 方法名称
     */
    @ApiModelProperty(value = "方法名称")
    @TableField("`method_name`")
    private String methodName;

    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    @TableField("`params`")
    private String params;

    /**
     * 参数类型列表
     */
    @ApiModelProperty(value = "参数类型列表")
    @TableField("`param_types`")
    private String paramTypes;

    /**
     * cron表达式
     */
    @ApiModelProperty(value = "cron表达式")
    @TableField("`cron_exp`")
    private String cronExp;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("`remark`")
    private String remark;

    /**
     * 运行状态
     */
    @ApiModelProperty(value = "运行状态")
    @TableField("`running_state`")
    private Byte runningState;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 乐观锁版本号
     */
    @ApiModelProperty(value = "乐观锁版本号")
    @TableField(value = "`version`", fill = FieldFill.INSERT)
    private Long version;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "`create_time`", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(value = "`update_time`", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 任务类型
     */
    @ApiModelProperty(value = "任务类型")
    @TableField("`type`")
    private Byte type;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @TableField("`start_time`")
    private LocalDateTime startTime;


}
