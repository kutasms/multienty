package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.TenantSubAccountRoleDTO;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.TenantSubAccountRoleMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.TenantSubAccountRole;
import com.chia.multienty.core.service.TenantSubAccountRoleService;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<String> getRoleNames(Long tenantSubAccountId) {
        List<TenantSubAccountRoleDTO> dtoList = baseMapper.selectJoinList(TenantSubAccountRoleDTO.class,
                new MPJLambdaWrapper<TenantSubAccountRole>()
                        .selectAs(Role::getAlias, TenantSubAccountRoleDTO::getRoleName)
                        .leftJoin(Role.class, Role::getRoleId, TenantSubAccountRole::getRoleId)
                        .eq(TenantSubAccountRole::getSubAccountId, tenantSubAccountId)
                        .eq(Role::getOwner, ApplicationType.MERCHANT.getValue())
        );
        if(dtoList!= null && dtoList.size() > 0) {
            return dtoList.stream().map(m->m.getRoleName()).collect(Collectors.toList());
        } else{
            return cn.hutool.core.collection.ListUtil.empty();
        }
    }

    @Override
    public String getRoleAlias(Long tenantSubAccountId) {
        return baseMapper.selectJoinOne(String.class,
                new MPJLambdaWrapper<TenantSubAccountRole>()
                        .select(Role::getAlias)
                        .leftJoin(Role.class, Role::getRoleId, TenantSubAccountRole::getRoleId)
                        .eq(TenantSubAccountRole::getSubAccountId, tenantSubAccountId)
                        .eq(Role::getOwner, ApplicationType.MERCHANT.getValue())
        );
    }

    @Override
    public List<Role> getRoles(Long tenantSubAccountId) {
        return baseMapper.selectJoinList(Role.class,
                new MPJLambdaWrapper<TenantSubAccountRole>()
                        .selectAll(Role.class)
                        .innerJoin(Role.class,
                                on-> on.eq(Role::getRoleId, TenantSubAccountRole::getRoleId)
                                        .eq(Role::getOwner, ApplicationType.MERCHANT.getValue())
                                        .eq(Role::getStatus, StatusEnum.NORMAL.getCode())
                        )
                        .eq(TenantSubAccountRole::getSubAccountId, tenantSubAccountId)
        );
    }

    @Override
    public List<TenantSubAccountRole> findRoles(List<Long> tenantSubAccountIds) {
        return list(
                new MPJLambdaWrapper<TenantSubAccountRole>()
                        .in(TenantSubAccountRole::getSubAccountId, tenantSubAccountIds)
        );
    }

    @Override
    public boolean change(Long tenantSubAccountId, Long roleId) {
        baseMapper.delete(new LambdaQueryWrapper<TenantSubAccountRole>()
                .eq(TenantSubAccountRole::getSubAccountId, tenantSubAccountId));
        TenantSubAccountRole tenantSubAccountRole = new TenantSubAccountRole();
        tenantSubAccountRole.setSubAccountId(tenantSubAccountId);
        tenantSubAccountRole.setRoleId(roleId);
        return baseMapper.insert(tenantSubAccountRole) == 1;
    }

    @Override
    public boolean setRoles(Long tenantSubAccountId, List<Long> roleIds) {
        baseMapper.delete(new LambdaQueryWrapper<TenantSubAccountRole>()
                .eq(TenantSubAccountRole::getSubAccountId, tenantSubAccountId));
        List<TenantSubAccountRole> roles = new ArrayList<>();
        for (Long roleId : roleIds) {
            TenantSubAccountRole role = new TenantSubAccountRole();
            role.setSubAccountId(tenantSubAccountId);
            role.setRoleId(roleId);
            roles.add(role);
        }
        return saveBatch(roles);
    }
}
