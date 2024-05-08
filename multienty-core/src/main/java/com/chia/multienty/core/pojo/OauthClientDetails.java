package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户端信息
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oauth_client_details")
@ApiModel(value="OauthClientDetails对象", description="客户端信息")
public class OauthClientDetails extends KutaBaseEntity {


    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID")
    @TableId(value = "client_id", type = IdType.INPUT)
    private String clientId;

    /**
     * 资源ID集合，多个资源时用英文逗号分隔
     */
    @ApiModelProperty(value = "资源ID集合，多个资源时用英文逗号分隔")
    @TableField("`resource_ids`")
    private String resourceIds;

    /**
     * 客户端密匙
     */
    @ApiModelProperty(value = "客户端密匙")
    @TableField("`client_secret`")
    private String clientSecret;

    /**
     * 客户端申请的权限范围
     */
    @ApiModelProperty(value = "客户端申请的权限范围")
    @TableField("`scope`")
    private String scope;

    /**
     * 客户端支持的grant_type
     */
    @ApiModelProperty(value = "客户端支持的grant_type")
    @TableField("`authorized_grant_types`")
    private String authorizedGrantTypes;

    /**
     * 重定向URI
     */
    @ApiModelProperty(value = "重定向URI")
    @TableField("`web_server_redirect_uri`")
    private String webServerRedirectUri;

    /**
     * 客户端所拥有的SpringSecurity的权限值，多个用英文逗号分隔
     */
    @ApiModelProperty(value = "客户端所拥有的SpringSecurity的权限值，多个用英文逗号分隔")
    @TableField("`authorities`")
    private String authorities;

    /**
     * 访问令牌有效时间值(单位秒)
     */
    @ApiModelProperty(value = "访问令牌有效时间值(单位秒)")
    @TableField("`access_token_validity`")
    private Integer accessTokenValidity;

    /**
     * 更新令牌有效时间值(单位秒)
     */
    @ApiModelProperty(value = "更新令牌有效时间值(单位秒)")
    @TableField("`refresh_token_validity`")
    private Integer refreshTokenValidity;

    /**
     * 预留字段
     */
    @ApiModelProperty(value = "预留字段")
    @TableField("`additional_information`")
    private String additionalInformation;

    /**
     * 用户是否自动Approval操作
     */
    @ApiModelProperty(value = "用户是否自动Approval操作")
    @TableField("`autoapprove`")
    private String autoapprove;


}
