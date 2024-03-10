package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.dto.TenantSubAccountRoleDTO;
import com.chia.multienty.core.mapper.TenantSubAccountRoleMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.TenantSubAccountRole;
import com.chia.multienty.core.service.TenantSubAccountRoleService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.user.TenantSubAccountRoleDetailGetParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountRolePageGetParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountRoleDeleteParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountRoleSaveParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountRoleUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 租户子账号角色关联 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */
@Service
public class TenantSubAccountRoleServiceImpl extends KutaBaseServiceImpl<TenantSubAccountRoleMapper, TenantSubAccountRole> implements TenantSubAccountRoleService {


    @Override
    public TenantSubAccountRoleDTO getDetail(TenantSubAccountRoleDetailGetParameter parameter) {
        return selectJoinOne(TenantSubAccountRoleDTO.class,
                        MPJWrappers.<TenantSubAccountRole>lambdaJoin().eq(TenantSubAccountRole::getSarId, parameter.getSarId()));
    }

    @Override
    public void delete(TenantSubAccountRoleDeleteParameter parameter) {
        removeByIdTE(parameter.getSarId());
    }

    @Override
    public IPage<TenantSubAccountRoleDTO> getPage(TenantSubAccountRolePageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), TenantSubAccountRoleDTO.class,
                new MTLambdaWrapper<TenantSubAccountRole>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getSarIds()), TenantSubAccountRole::getSarId, parameter.getSarIds())
        );
    }

    @Override
    public void save(TenantSubAccountRoleSaveParameter parameter) {
        TenantSubAccountRole tenantSubAccountRole = new TenantSubAccountRole();
        BeanUtils.copyProperties(parameter, tenantSubAccountRole);
        saveTE(tenantSubAccountRole);
        parameter.setSarId(tenantSubAccountRole.getSarId());
    }

    @Override
    public void update(TenantSubAccountRoleUpdateParameter parameter) {
        TenantSubAccountRole tenantSubAccountRole = new TenantSubAccountRole();
        BeanUtils.copyProperties(parameter, tenantSubAccountRole);
        updateByIdTE(tenantSubAccountRole);
    }
}
