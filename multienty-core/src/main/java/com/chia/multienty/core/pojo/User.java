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
 * 用户
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_user")
@ApiModel(value="User对象", description="用户")
public class User extends KutaBaseEntity {


    /**
     * 用户编号
     */
    @ApiModelProperty(value = "用户编号")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @TableField("`username`")
    private String username;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @TableField("`name`")
    private String name;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @TableField("`sex`")
    private Byte sex;

    /**
     * 账户手机号
     */
    @ApiModelProperty(value = "账户手机号")
    @TableField("`phone`")
    private String phone;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    @TableField("`password`")
    private String password;

    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号")
    @TableField("`app_id`")
    private Byte appId;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField("`email`")
    private String email;

    /**
     * 令牌
     */
    @ApiModelProperty(value = "令牌")
    @TableField("`token`")
    private String token;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    @TableField("`avatar`")
    private String avatar;

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
