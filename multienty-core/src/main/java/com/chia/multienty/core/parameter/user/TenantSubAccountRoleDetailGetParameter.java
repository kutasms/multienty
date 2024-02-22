package com.chia.multienty.core.parameter.user;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 租户子账号角色关联详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "TenantSubAccountRoleDetailGetParameter",description = "租户子账号角色关联详情获取请求")
public class TenantSubAccountRoleDetailGetParameter {
    /**
     * 关联编号
     */
     @ApiModelProperty(value = "关联编号")
     private Long sarId;
}
