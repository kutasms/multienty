package com.chia.multienty.core.parameter.tenant;

import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 密钥授权禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "SecretAuthDisableParameter",description = "密钥授权禁用请求")
@Accessors(chain = true)
public class SecretAuthDisableParameter {
    /**
     * 密钥授权编号
     */
     @ApiModelProperty(value = "密钥授权编号")
     @LogMetaId
     private Long secAuthId;
}
