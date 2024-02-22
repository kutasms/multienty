package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 租户禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantDisableParameter",description = "租户禁用请求")
public class TenantDisableParameter {
    /**
     * 租户编号
     */
     @ApiModelProperty(value = "租户编号")
     private Long tenantId;
}
