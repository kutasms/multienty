package com.chia.multienty.core.parameter.user;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 权限禁用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "PermissionDisableParameter",description = "权限禁用请求")
public class PermissionDisableParameter {
    /**
     * 权限编号
     */
     @ApiModelProperty(value = "权限编号")
     private Long permissionId;
}
