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
 * 微信公众号账户
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_wechat_official_account")
@ApiModel(value="WechatOfficialAccount对象", description="微信公众号账户")
public class WechatOfficialAccount extends KutaBaseEntity {


    /**
     * 微信公众号账户编号
     */
    @ApiModelProperty(value = "微信公众号账户编号")
    @TableId(value = "woa_id", type = IdType.AUTO)
    private Long woaId;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField("`tenant_id`")
    private Long tenantId;

    /**
     * 应用编号
     */
    @ApiModelProperty(value = "应用编号")
    @TableField("`app_id`")
    private String appId;

    /**
     * 应用密钥
     */
    @ApiModelProperty(value = "应用密钥")
    @TableField("`app_secret`")
    private String appSecret;

    /**
     * 令牌
     */
    @ApiModelProperty(value = "令牌")
    @TableField("`token`")
    private String token;

    /**
     * AES密钥
     */
    @ApiModelProperty(value = "AES密钥")
    @TableField("`aes_key`")
    private String aesKey;

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
