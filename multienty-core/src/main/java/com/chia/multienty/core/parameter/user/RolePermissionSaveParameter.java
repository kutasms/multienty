package com.chia.multienty.core.parameter.user;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 账户角色关联权限实体保存请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */

@Data
@ApiModel(value = "RolePermissionSaveParameter",description = "账户角色关联权限实体保存请求")
public class RolePermissionSaveParameter {

    /**
     * 关联id
     */
    @ApiModelProperty(value = "关联id")
    @LogMetaId
    @JsonIgnore
    @JsonProperty(required = false)
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
