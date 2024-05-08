package com.chia.multienty.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.TenantSubAccountRoleDTO;
import com.chia.multienty.core.mybatis.service.KutaBaseService;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.TenantSubAccountRole;

import java.util.List;

/**
 * <p>
 * 租户子账号角色关联 服务类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */
public interface TenantSubAccountRoleService extends KutaBaseService<TenantSubAccountRole> {

    TenantSubAccountRoleDTO getDetail(TenantSubAccountRoleDetailGetParameter parameter);

    void delete(TenantSubAccountRoleDeleteParameter parameter);

    IPage<TenantSubAccountRoleDTO> getPage(TenantSubAccountRolePageGetParameter parameter);

    void save(TenantSubAccountRoleSaveParameter parameter);

    void update(TenantSubAccountRoleUpdateParameter parameter);

    List<String> getRoleNames(Long tenantSubAccountId);

    String getRoleAlias(Long tenantSubAccountId);

    List<Role> getRoles(Long tenantSubAccountId);

    List<TenantSubAccountRole> findRoles(List<Long> tenantSubAccountIds);

    boolean change(Long tenantSubAccountId, Long roleId);

    boolean setRoles(Long tenantSubAccountId, List<Long> roleIds);
}
