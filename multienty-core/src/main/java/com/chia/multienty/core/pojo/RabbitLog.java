package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * RabbitMQ日志
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_rabbit_log")
@ApiModel(value="RabbitLog对象", description="RabbitMQ日志")
public class RabbitLog extends KutaBaseEntity {


    /**
     * 发送记录编号
     */
    @ApiModelProperty(value = "发送记录编号")
    @TableId(value = "rid", type = IdType.AUTO)
    private Long rid;

    /**
     * 键
     */
    @ApiModelProperty(value = "键")
    @TableField("`key`")
    private String key;

    /**
     * 元编号
     */
    @ApiModelProperty(value = "元编号")
    @TableField("`meta_id`")
    private Long metaId;

    /**
     * 发送时间
     */
    @ApiModelProperty(value = "发送时间")
    @TableField("`sent_time`")
    private LocalDateTime sentTime;

    /**
     * 消息
     */
    @ApiModelProperty(value = "消息")
    @TableField("`message`")
    private String message;

    /**
     * 预定发送时间刻度数
     */
    @ApiModelProperty(value = "预定发送时间刻度数")
    @TableField("`timestamp`")
    private Long timestamp;

    /**
     * 延迟时间
     */
    @ApiModelProperty(value = "延迟时间")
    @TableField("`delay_time`")
    private Long delayTime;

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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField(value = "`status`", fill = FieldFill.INSERT)
    private String status;

    /**
     * 错误消息
     */
    @ApiModelProperty(value = "错误消息")
    @TableField("`error_msg`")
    private String errorMsg;

    /**
     * 路由键
     */
    @ApiModelProperty(value = "路由键")
    @TableField("`routing_key`")
    private String routingKey;

    /**
     * 数据类型
     */
    @ApiModelProperty(value = "数据类型")
    @TableField("`data_type`")
    private String dataType;

    /**
     * 业务类型
     */
    @ApiModelProperty(value = "业务类型")
    @TableField("`bo_type`")
    private String boType;

    /**
     * 是否延迟消息
     */
    @ApiModelProperty(value = "是否延迟消息")
    @TableField("`delayed`")
    private Boolean delayed;

    /**
     * 是否独立模式
     */
    @ApiModelProperty(value = "是否独立模式")
    @TableField("`idempotent`")
    private Boolean idempotent;

    /**
     * 乐观锁版本号
     */
    @ApiModelProperty(value = "乐观锁版本号")
    @TableField(value = "`version`", fill = FieldFill.INSERT)
    private Long version;

    /**
     * 是否半开模式
     */
    @ApiModelProperty(value = "是否半开模式")
    @TableField("`half_mode`")
    private Boolean halfMode;


}
