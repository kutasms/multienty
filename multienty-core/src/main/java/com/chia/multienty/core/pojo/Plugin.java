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
 * 插件
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_plugin")
@ApiModel(value="Plugin对象", description="插件")
public class Plugin extends KutaBaseEntity {


    /**
     * 插件编号
     */
    @ApiModelProperty(value = "插件编号")
    @TableId(value = "plugin_id", type = IdType.INPUT)
    private Long pluginId;

    /**
     * 插件名称
     */
    @ApiModelProperty(value = "插件名称")
    @TableField("`name`")
    private String name;

    /**
     * 插件类型
     */
    @ApiModelProperty(value = "插件类型")
    @TableField("`type`")
    private Short type;

    /**
     * 插件类型名称
     */
    @ApiModelProperty(value = "插件类型名称")
    @TableField("`type_name`")
    private String typeName;

    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    @TableField("`alias`")
    private String alias;

    /**
     * 详情
     */
    @ApiModelProperty(value = "详情")
    @TableField("`details`")
    private String details;

    /**
     * 运行模式
     */
    @ApiModelProperty(value = "运行模式")
    @TableField("`running_mode`")
    private Byte runningMode;

    /**
     * 来源
     */
    @ApiModelProperty(value = "来源")
    @TableField("`source`")
    private String source;

    /**
     * 运行状态
     */
    @ApiModelProperty(value = "运行状态")
    @TableField("`running_state`")
    private String runningState;

    /**
     * 库名称
     */
    @ApiModelProperty(value = "库名称")
    @TableField("`lib_name`")
    private String libName;

    /**
     * 包名称
     */
    @ApiModelProperty(value = "包名称")
    @TableField("`package_name`")
    private String packageName;

    /**
     * 类名称
     */
    @ApiModelProperty(value = "类名称")
    @TableField("`class_name`")
    private String className;

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
     * 乐观锁版本号
     */
    @ApiModelProperty(value = "乐观锁版本号")
    @TableField(value = "`version`", fill = FieldFill.INSERT)
    private Long version;


}
