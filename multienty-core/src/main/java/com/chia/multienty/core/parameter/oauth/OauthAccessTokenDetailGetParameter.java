package com.chia.multienty.core.parameter.oauth;

import lombok.Data;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
/**
 * <p>
 * 访问令牌详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "OauthAccessTokenDetailGetParameter",description = "访问令牌详情获取请求")
public class OauthAccessTokenDetailGetParameter {
    /**
     * MD5加密过的username,client_id,scope
     */
     @ApiModelProperty(value = "MD5加密过的username,client_id,scope")
     private String authenticationId;
}
