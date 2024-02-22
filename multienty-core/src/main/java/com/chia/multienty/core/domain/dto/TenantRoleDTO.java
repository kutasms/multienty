package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.TenantRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 租户角色关联 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TenantRoleDTO", description="租户角色关联DTO对象")
public class TenantRoleDTO extends TenantRole {
    private String roleName;
}
