package com.chia.multienty.core.domain.dto;

import com.chia.multienty.core.pojo.TenantSubAccountRole;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 租户子账号角色关联 DTO对象
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="TenantSubAccountRoleDTO", description="租户子账号角色关联DTO对象")
public class TenantSubAccountRoleDTO extends TenantSubAccountRole {
}
