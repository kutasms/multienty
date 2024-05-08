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
 * 访问令牌
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oauth_access_token")
@ApiModel(value="OauthAccessToken对象", description="访问令牌")
public class OauthAccessToken extends KutaBaseEntity {


    /**
     * MD5加密的access_token的值
     */
    @ApiModelProperty(value = "MD5加密的access_token的值")
    @TableField("`token_id`")
    private String tokenId;

    /**
     * OAuth2AccessToken.java对象序列化后的二进制数据
     */
    @ApiModelProperty(value = "OAuth2AccessToken.java对象序列化后的二进制数据")
    @TableField("`token`")
    private byte[] token;

    /**
     * MD5加密过的username,client_id,scope
     */
    @ApiModelProperty(value = "MD5加密过的username,client_id,scope")
    @TableId(value = "authentication_id", type = IdType.INPUT)
    private String authenticationId;

    /**
     * 登录的用户名
     */
    @ApiModelProperty(value = "登录的用户名")
    @TableField("`user_name`")
    private String userName;

    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID")
    @TableField("`client_id`")
    private String clientId;

    /**
     * OAuth2Authentication.java对象序列化后的二进制数据
     */
    @ApiModelProperty(value = "OAuth2Authentication.java对象序列化后的二进制数据")
    @TableField("`authentication`")
    private byte[] authentication;

    /**
     * MD5加密后的refresh_token的值
     */
    @ApiModelProperty(value = "MD5加密后的refresh_token的值")
    @TableField("`refresh_token`")
    private String refreshToken;


}
