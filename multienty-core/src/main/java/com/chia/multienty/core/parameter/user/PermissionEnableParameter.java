package com.chia.multienty.core.parameter.user;

import com.chia.multienty.core.annotation.LogMetaId;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 权限启用请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "PermissionEnableParameter",description = "权限启用请求")
public class PermissionEnableParameter {
    /**
     * 权限编号
     */
     @ApiModelProperty(value = "权限编号")
     @LogMetaId
     private Long permissionId;
}
