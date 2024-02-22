package com.chia.multienty.core.parameter.user;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 账户角色关联权限实体详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "RolePermissionDetailGetParameter",description = "账户角色关联权限实体详情获取请求")
public class RolePermissionDetailGetParameter {
    /**
     * 关联id
     */
     @ApiModelProperty(value = "关联id")
     private Long rpId;
}
