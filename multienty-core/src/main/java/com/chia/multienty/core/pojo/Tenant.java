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
 * 租户
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_tenant")
@ApiModel(value="Tenant对象", description="租户")
public class Tenant extends KutaBaseEntity {


    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableId(value = "tenant_id", type = IdType.INPUT)
    private Long tenantId;

    /**
     * 租户号码
     */
    @ApiModelProperty(value = "租户号码")
    @TableField("`tenant_no`")
    private String tenantNo;

    /**
     * 帐号
     */
    @ApiModelProperty(value = "帐号")
    @TableField("`username`")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField("`password`")
    private String password;

    /**
     * 是否已过期
     */
    @ApiModelProperty(value = "是否已过期")
    @TableField("`expired`")
    private Boolean expired;

    /**
     * 是否已锁定
     */
    @ApiModelProperty(value = "是否已锁定")
    @TableField("`locked`")
    private Boolean locked;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    @TableField("`company_name`")
    private String companyName;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    @TableField("`phone_number`")
    private String phoneNumber;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    @TableField("`liaison`")
    private String liaison;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    @TableField("`contact_number`")
    private String contactNumber;

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
     * 租户类型
     */
    @ApiModelProperty(value = "租户类型")
    @TableField("`type`")
    private Byte type;

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
