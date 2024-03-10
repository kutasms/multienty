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
 * 权限
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_permission")
@ApiModel(value="Permission对象", description="权限")
public class Permission extends KutaBaseEntity {


    /**
     * 权限编号
     */
    @ApiModelProperty(value = "权限编号")
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Long permissionId;

    /**
     * 父节点id
     */
    @ApiModelProperty(value = "父节点id")
    @TableField("`pid`")
    private Long pid;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @TableField("`name`")
    private String name;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径")
    @TableField("`path`")
    private String path;

    /**
     * 后端api接口
     */
    @ApiModelProperty(value = "后端api接口")
    @TableField("`api`")
    private String api;

    /**
     * 组件
     */
    @ApiModelProperty(value = "组件")
    @TableField("`component`")
    private String component;

    /**
     * 图标地址
     */
    @ApiModelProperty(value = "图标地址")
    @TableField("`icon`")
    private String icon;

    /**
     * 文字描述
     */
    @ApiModelProperty(value = "文字描述")
    @TableField("`description`")
    private String description;

    /**
     * 拥有者
     */
    @ApiModelProperty(value = "拥有者")
    @TableField("`owner`")
    private Long owner;

    /**
     * 权限类型
     */
    @ApiModelProperty(value = "权限类型")
    @TableField("`type`")
    private Byte type;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    @TableField("`sort`")
    private Integer sort;

    /**
     * 应用id
     */
    @ApiModelProperty(value = "应用id")
    @TableField("`app_id`")
    private Byte appId;

    /**
     * 是否隐藏菜单
     */
    @ApiModelProperty(value = "是否隐藏菜单")
    @TableField("`hidden`")
    private Boolean hidden;

    /**
     * 重定向
     */
    @ApiModelProperty(value = "重定向")
    @TableField("`redirect`")
    private String redirect;

    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    @TableField("`alias`")
    private String alias;

    /**
     * 是否固定到导航条
     */
    @ApiModelProperty(value = "是否固定到导航条")
    @TableField("`affix`")
    private Boolean affix;

    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    @TableField("`hierarchy`")
    private String hierarchy;

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
     * 乐观锁版本号
     */
    @ApiModelProperty(value = "乐观锁版本号")
    @TableField(value = "`version`", fill = FieldFill.INSERT)
    private Long version;

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
