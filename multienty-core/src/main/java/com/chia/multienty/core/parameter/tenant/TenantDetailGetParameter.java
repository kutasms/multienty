package com.chia.multienty.core.parameter.tenant;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.Accessors;

/**
 * <p>
 * 租户信息详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@Accessors(chain = true)
@ApiModel(value = "TenantDetailGetParameter",description = "租户信息详情获取请求")
public class TenantDetailGetParameter implements Serializable {
    /**
     * 租户id
     */
     @ApiModelProperty(value = "租户id")
     private Long tenantId;
}
