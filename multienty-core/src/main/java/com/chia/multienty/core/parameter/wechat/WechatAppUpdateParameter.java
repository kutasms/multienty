package com.chia.multienty.core.parameter.wechat;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 微信应用更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */

@Data
@ApiModel(value = "WechatAppUpdateParameter",description = "微信应用更新请求")
public class WechatAppUpdateParameter {

     @LogMetaId
     private Long programId;
    /**
     * 租户编号
     */
     @ApiModelProperty(value = "租户编号")
     private Long tenantId;
    /**
     * 小程序应用编号
     */
     @ApiModelProperty(value = "小程序应用编号")
     private String miniAppId;
    /**
     * Native应用编号
     */
     @ApiModelProperty(value = "Native应用编号")
     private String nativeAppId;
    /**
     * 密钥
     */
     @ApiModelProperty(value = "密钥")
     private String secret;
    /**
     * 创建时间
     */
     @ApiModelProperty(value = "创建时间")
     private LocalDateTime createTime;
    /**
     * 状态
     */
     @ApiModelProperty(value = "状态")
     private String status;
    /**
     * 是否已删除
     */
     @ApiModelProperty(value = "是否已删除")
     private Boolean deleted;
    /**
     * 商户接口调用凭证
     */
     @ApiModelProperty(value = "商户接口调用凭证")
     private String accessToken;
    /**
     * 预授权码
     */
     @ApiModelProperty(value = "预授权码")
     private String preAuthCode;
    /**
     * 授权码
     */
     @ApiModelProperty(value = "授权码")
     private String authorizationCode;
    /**
     * 授权方令牌
     */
     @ApiModelProperty(value = "授权方令牌")
     private String authorizerAccessToken;
    /**
     * 刷新令牌
     */
     @ApiModelProperty(value = "刷新令牌")
     private String authorizerRefreshToken;
    /**
     * 授权token最后更新时间
     */
     @ApiModelProperty(value = "授权token最后更新时间")
     private LocalDateTime authorizerTokenTime;
    /**
     * 过期时间
     */
     @ApiModelProperty(value = "过期时间")
     private Integer authorizerTokenExpiresIn;
}
