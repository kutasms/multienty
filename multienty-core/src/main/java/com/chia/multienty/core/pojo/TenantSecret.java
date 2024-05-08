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
 * 租户密钥
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_tenant_secret")
@ApiModel(value="TenantSecret对象", description="租户密钥")
public class TenantSecret extends KutaBaseEntity {


    /**
     * 密钥编号
     */
    @ApiModelProperty(value = "密钥编号")
    @TableId(value = "secret_id", type = IdType.INPUT)
    private Long secretId;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField("`tenant_id`")
    private Long tenantId;

    /**
     * 子账号编号
     */
    @ApiModelProperty(value = "子账号编号")
    @TableField("`sub_acc_id`")
    private Long subAccId;

    /**
     * 应用key
     */
    @ApiModelProperty(value = "应用key")
    @TableField("`app_key`")
    private String appKey;

    /**
     * 应用密钥
     */
    @ApiModelProperty(value = "应用密钥")
    @TableField("`app_secret`")
    private String appSecret;

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
