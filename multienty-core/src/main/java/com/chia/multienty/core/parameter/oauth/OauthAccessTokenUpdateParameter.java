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
 * 访问令牌更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */

@Data
@ApiModel(value = "OauthAccessTokenUpdateParameter",description = "访问令牌更新请求")
@Accessors(chain = true)
public class OauthAccessTokenUpdateParameter {

    /**
     * MD5加密的access_token的值
     */
     @ApiModelProperty(value = "MD5加密的access_token的值")
     private String tokenId;
    /**
     * OAuth2AccessToken.java对象序列化后的二进制数据
     */
     @ApiModelProperty(value = "OAuth2AccessToken.java对象序列化后的二进制数据")
     private byte[] token;
    /**
     * MD5加密过的username,client_id,scope
     */
     @ApiModelProperty(value = "MD5加密过的username,client_id,scope")
     @LogMetaId
     private String authenticationId;
    /**
     * 登录的用户名
     */
     @ApiModelProperty(value = "登录的用户名")
     private String userName;
    /**
     * 客户端ID
     */
     @ApiModelProperty(value = "客户端ID")
     private String clientId;
    /**
     * OAuth2Authentication.java对象序列化后的二进制数据
     */
     @ApiModelProperty(value = "OAuth2Authentication.java对象序列化后的二进制数据")
     private byte[] authentication;
    /**
     * MD5加密后的refresh_token的值
     */
     @ApiModelProperty(value = "MD5加密后的refresh_token的值")
     private String refreshToken;
}
