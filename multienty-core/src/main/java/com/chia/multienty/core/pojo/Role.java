package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_role")
@ApiModel(value="Role对象", description="角色")
public class Role extends KutaBaseEntity {


    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @TableField("`name`")
    private String name;

    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    @TableField("`alias`")
    private String alias;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述")
    @TableField("`description`")
    private String description;

    /**
     * 拥有者
     */
    @ApiModelProperty(value = "拥有者")
    @TableField("`owner`")
    private Long owner;

    /**
     * 可编辑
     */
    @ApiModelProperty(value = "可编辑")
    @TableField("`editable`")
    private Boolean editable;

    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号")
    @TableField("`app_id`")
    private Byte appId;

    /**
     * 乐观锁版本号
     */
    @ApiModelProperty(value = "乐观锁版本号")
    @TableField(value = "`version`", fill = FieldFill.INSERT)
    private Long version;

    /**
     * 可删除
     */
    @ApiModelProperty(value = "可删除")
    @TableField("`deletable`")
    private Boolean deletable;

    /**
     * 是否超级管理员
     */
    @ApiModelProperty(value = "是否超级管理员")
    @TableField("`super_admin`")
    private Boolean superAdmin;

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
