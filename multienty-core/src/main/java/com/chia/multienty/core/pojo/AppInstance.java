package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * 应用实例
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_app_instance")
@ApiModel(value="AppInstance对象", description="应用实例")
public class AppInstance extends KutaBaseEntity {


    /**
     * 实例编号
     */
    @ApiModelProperty(value = "实例编号")
    @TableId(value = "instance_id", type = IdType.INPUT)
    private Long instanceId;

    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号")
    @TableField("`app_id`")
    private Long appId;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField("`tenant_id`")
    private Long tenantId;

    /**
     * 运行模式
     */
    @ApiModelProperty(value = "运行模式")
    @TableField("`running_mode`")
    private Byte runningMode;

    /**
     * 截至日期
     */
    @ApiModelProperty(value = "截至日期")
    @TableField("`deadline`")
    private LocalDateTime deadline;

    /**
     * 实例状态
     */
    @ApiModelProperty(value = "实例状态")
    @TableField("`state`")
    private String state;

    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    @TableField("`alias`")
    private String alias;

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
     * 是否已删除
     */
    @ApiModelProperty(value = "是否已删除")
    @TableField(value = "`deleted`", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;


}
