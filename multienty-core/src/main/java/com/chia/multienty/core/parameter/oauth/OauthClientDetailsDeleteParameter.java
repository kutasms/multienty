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
 * 客户端信息删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-04
 */

@Data
@ApiModel(value = "OauthClientDetailsDeleteParameter",description = "客户端信息删除请求")
@Accessors(chain = true)
public class OauthClientDetailsDeleteParameter {

    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID")
    @LogMetaId
    private String clientId;

}
