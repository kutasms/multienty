package com.chia.multienty.core.parameter.oauth;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 更新令牌保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */

@Data
@ApiModel(value = "OauthRefreshTokenSaveParameter",description = "更新令牌保存请求")
@Accessors(chain = true)
public class OauthRefreshTokenSaveParameter {

    /**
     * MD5加密过的refresh_token的值
     */
    @ApiModelProperty(value = "MD5加密过的refresh_token的值")
    private String tokenId;
    /**
     * OAuth2RefreshToken.java对象序列化后的二进制数据
     */
    @ApiModelProperty(value = "OAuth2RefreshToken.java对象序列化后的二进制数据")
    private byte[] token;
    /**
     * OAuth2Authentication.java对象序列化后的二进制数据
     */
    @ApiModelProperty(value = "OAuth2Authentication.java对象序列化后的二进制数据")
    private byte[] authentication;
}
