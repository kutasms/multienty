package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "RolePermissionChangeParameter",description = "角色权限变更请求")
public class RolePermissionChangeParameter {
    @ApiModelProperty("角色编号")
    @LogMetaId
    private Long roleId;

    @ApiModelProperty("权限编号列表")
    private List<Long> permissionIds;
}
