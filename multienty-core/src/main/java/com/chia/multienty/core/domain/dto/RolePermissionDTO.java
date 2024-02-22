package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.RolePermission;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 账户角色关联权限实体 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="RolePermissionDTO", description="账户角色关联权限实体DTO对象")
public class RolePermissionDTO extends RolePermission {
}
