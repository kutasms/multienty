package com.chia.multienty.core.parameter.oauth;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 客户端信息更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */

@Data
@ApiModel(value = "OauthClientDetailsUpdateParameter",description = "客户端信息更新请求")
@Accessors(chain = true)
public class OauthClientDetailsUpdateParameter {

    /**
     * 客户端ID
     */
     @ApiModelProperty(value = "客户端ID")
     @LogMetaId
     private String clientId;
    /**
     * 资源ID集合，多个资源时用英文逗号分隔
     */
     @ApiModelProperty(value = "资源ID集合，多个资源时用英文逗号分隔")
     private String resourceIds;
    /**
     * 客户端密匙
     */
     @ApiModelProperty(value = "客户端密匙")
     private String clientSecret;
    /**
     * 客户端申请的权限范围
     */
     @ApiModelProperty(value = "客户端申请的权限范围")
     private String scope;
    /**
     * 客户端支持的grant_type
     */
     @ApiModelProperty(value = "客户端支持的grant_type")
     private String authorizedGrantTypes;
    /**
     * 重定向URI
     */
     @ApiModelProperty(value = "重定向URI")
     private String webServerRedirectUri;
    /**
     * 客户端所拥有的SpringSecurity的权限值，多个用英文逗号分隔
     */
     @ApiModelProperty(value = "客户端所拥有的SpringSecurity的权限值，多个用英文逗号分隔")
     private String authorities;
    /**
     * 访问令牌有效时间值(单位秒)
     */
     @ApiModelProperty(value = "访问令牌有效时间值(单位秒)")
     private Integer accessTokenValidity;
    /**
     * 更新令牌有效时间值(单位秒)
     */
     @ApiModelProperty(value = "更新令牌有效时间值(单位秒)")
     private Integer refreshTokenValidity;
    /**
     * 预留字段
     */
     @ApiModelProperty(value = "预留字段")
     private String additionalInformation;
    /**
     * 用户是否自动Approval操作
     */
     @ApiModelProperty(value = "用户是否自动Approval操作")
     private String autoapprove;
}
