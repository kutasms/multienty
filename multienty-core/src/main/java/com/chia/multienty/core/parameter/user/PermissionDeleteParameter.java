package com.chia.multienty.core.parameter.user;

import lombok.Data;
import com.chia.multienty.core.annotation.LogMetaId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 权限删除请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */

@Data
@ApiModel(value = "PermissionDeleteParameter",description = "权限删除请求")
public class PermissionDeleteParameter {

    /**
     * 权限编号
     */
     @ApiModelProperty(value = "权限编号")
     @LogMetaId
     private Long permissionId;
}
