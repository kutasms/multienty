package com.chia.multienty.core.parameter.user;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 账户角色关联权限实体更新请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "RolePermissionUpdateParameter",description = "账户角色关联权限实体更新请求")
public class RolePermissionUpdateParameter {

        /**
         * 关联id
         */
        @ApiModelProperty(value = "关联id")
        private Long rpId;
        /**
         * 关联角色id
         */
        @ApiModelProperty(value = "关联角色id")
        private Long roleId;
        /**
         * 关联权限id
         */
        @ApiModelProperty(value = "关联权限id")
        private Long permissionId;
}
