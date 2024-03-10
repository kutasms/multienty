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
 * 微信应用
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mt_wechat_app")
@ApiModel(value="WechatApp对象", description="微信应用")
public class WechatApp extends KutaBaseEntity {


    @TableId(value = "program_id", type = IdType.AUTO)
    private Long programId;

    /**
     * 租户编号
     */
    @ApiModelProperty(value = "租户编号")
    @TableField("`tenant_id`")
    private Long tenantId;

    /**
     * 小程序应用编号
     */
    @ApiModelProperty(value = "小程序应用编号")
    @TableField("`mini_app_id`")
    private String miniAppId;

    /**
     * Native应用编号
     */
    @ApiModelProperty(value = "Native应用编号")
    @TableField("`native_app_id`")
    private String nativeAppId;

    /**
     * 密钥
     */
    @ApiModelProperty(value = "密钥")
    @TableField("`secret`")
    private String secret;

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

    /**
     * 商户接口调用凭证
     */
    @ApiModelProperty(value = "商户接口调用凭证")
    @TableField("`access_token`")
    private String accessToken;

    /**
     * 预授权码
     */
    @ApiModelProperty(value = "预授权码")
    @TableField("`pre_auth_code`")
    private String preAuthCode;

    /**
     * 授权码
     */
    @ApiModelProperty(value = "授权码")
    @TableField("`authorization_code`")
    private String authorizationCode;

    /**
     * 授权方令牌
     */
    @ApiModelProperty(value = "授权方令牌")
    @TableField("`authorizer_access_token`")
    private String authorizerAccessToken;

    /**
     * 刷新令牌
     */
    @ApiModelProperty(value = "刷新令牌")
    @TableField("`authorizer_refresh_token`")
    private String authorizerRefreshToken;

    /**
     * 授权token最后更新时间
     */
    @ApiModelProperty(value = "授权token最后更新时间")
    @TableField("`authorizer_token_time`")
    private LocalDateTime authorizerTokenTime;

    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    @TableField("`authorizer_token_expires_in`")
    private Integer authorizerTokenExpiresIn;


}
