package com.chia.multienty.core.parameter.user;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 角色权限关联数据删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "RolePermissionDeleteParameter",description = "角色权限关联数据删除请求")
public class RolePermissionDeleteParameter {

    /**
     * 关联编号
     */
     @ApiModelProperty(value = "关联编号")
     @LogMetaId
     private Long rpId;
}
