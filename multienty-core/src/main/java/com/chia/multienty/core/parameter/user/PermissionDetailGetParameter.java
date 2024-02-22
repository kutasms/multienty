package com.chia.multienty.core.parameter.user;

import lombok.Data;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * 权限菜单信息详情获取请求
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-06
 */

@Data
@ApiModel(value = "PermissionDetailGetParameter",description = "权限菜单信息详情获取请求")
public class PermissionDetailGetParameter {
    /**
     * 权限id
     */
     @ApiModelProperty(value = "权限id")
     private Long permissionId;
}
