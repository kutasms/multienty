package com.chia.multienty.core.parameter.oauth;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import javax.validation.constraints.NotNull;
import com.chia.multienty.core.pojo.KutaBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;
/**
 * <p>
 * 访问令牌删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */

@Data
@ApiModel(value = "OauthAccessTokenDeleteParameter",description = "访问令牌删除请求")
@Accessors(chain = true)
public class OauthAccessTokenDeleteParameter {

    /**
     * MD5加密过的username,client_id,scope
     */
    @ApiModelProperty(value = "MD5加密过的username,client_id,scope")
    @LogMetaId
    private String authenticationId;

}
