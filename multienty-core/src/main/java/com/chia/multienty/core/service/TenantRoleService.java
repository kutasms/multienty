package com.chia.multienty.core.service;

import com.chia.multienty.core.domain.dto.TenantRoleDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.TenantRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.tenant.TenantRoleDetailGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantRolePageGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantRoleDeleteParameter;
import com.chia.multienty.core.parameter.tenant.TenantRoleSaveParameter;
import com.chia.multienty.core.parameter.tenant.TenantRoleUpdateParameter;

import java.util.List;

/**
 * <p>
 * 租户角色关联 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
public interface TenantRoleService extends KutaBaseService<TenantRole> {

    TenantRoleDTO getDetail(TenantRoleDetailGetParameter parameter);

    void delete(TenantRoleDeleteParameter parameter);

    IPage<TenantRoleDTO> getPage(TenantRolePageGetParameter parameter);

    void save(TenantRoleSaveParameter parameter);

    void update(TenantRoleUpdateParameter parameter);

    List<String> getRoleNames(Long tenantId);

    String getRoleAlias(Long tenantId);

    List<Role> getRoles(Long tenantId);

    boolean change(Long tenantId, Long roleId);

    boolean setRoles(Long tenantId, List<Long> roleIds);
}
