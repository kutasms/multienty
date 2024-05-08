package com.chia.multienty.core.parameter.tenant;

import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 租户密钥启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-03-27
 */

@Data
@ApiModel(value = "TenantSecretEnableParameter",description = "租户密钥启用请求")
@Accessors(chain = true)
public class TenantSecretEnableParameter {
    /**
     * 密钥编号
     */
     @ApiModelProperty(value = "密钥编号")
     @LogMetaId
     private Long secretId;
}
