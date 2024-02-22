package com.chia.multienty.core.parameter.user;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 角色启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "RoleEnableParameter",description = "角色启用请求")
public class RoleEnableParameter {
    /**
     * 角色编号
     */
     @ApiModelProperty(value = "角色编号")
     private Long roleId;
}
