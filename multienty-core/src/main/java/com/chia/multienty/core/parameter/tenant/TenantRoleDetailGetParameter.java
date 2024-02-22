package com.chia.multienty.core.parameter.tenant;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 租户角色关联详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */

@Data
@ApiModel(value = "TenantRoleDetailGetParameter",description = "租户角色关联详情获取请求")
public class TenantRoleDetailGetParameter {
    /**
     * 关联编号
     */
     @ApiModelProperty(value = "关联编号")
     private Long trId;
}
