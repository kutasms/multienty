package com.chia.multienty.core.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 更新令牌
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("oauth_refresh_token")
@ApiModel(value="OauthRefreshToken对象", description="更新令牌")
public class OauthRefreshToken extends KutaBaseEntity {


    /**
     * MD5加密过的refresh_token的值
     */
    @ApiModelProperty(value = "MD5加密过的refresh_token的值")
    @TableField("`token_id`")
    private String tokenId;

    /**
     * OAuth2RefreshToken.java对象序列化后的二进制数据
     */
    @ApiModelProperty(value = "OAuth2RefreshToken.java对象序列化后的二进制数据")
    @TableField("`token`")
    private byte[] token;

    /**
     * OAuth2Authentication.java对象序列化后的二进制数据
     */
    @ApiModelProperty(value = "OAuth2Authentication.java对象序列化后的二进制数据")
    @TableField("`authentication`")
    private byte[] authentication;


}
