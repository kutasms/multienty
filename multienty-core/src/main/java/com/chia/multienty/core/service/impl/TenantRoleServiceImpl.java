package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chia.multienty.core.domain.dto.TenantRoleDTO;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.TenantRoleMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.TenantRole;
import com.chia.multienty.core.service.TenantRoleService;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.tenant.TenantRoleDetailGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantRolePageGetParameter;
import com.chia.multienty.core.parameter.tenant.TenantRoleDeleteParameter;
import com.chia.multienty.core.parameter.tenant.TenantRoleSaveParameter;
import com.chia.multienty.core.parameter.tenant.TenantRoleUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 租户角色关联 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@Service
public class TenantRoleServiceImpl extends KutaBaseServiceImpl<TenantRoleMapper, TenantRole> implements TenantRoleService {


    @Override
    public TenantRoleDTO getDetail(TenantRoleDetailGetParameter parameter) {
        return selectJoinOne(TenantRoleDTO.class,
                        MPJWrappers.<TenantRole>lambdaJoin().eq(TenantRole::getTrId, parameter.getTrId()));
    }

    @Override
    public void delete(TenantRoleDeleteParameter parameter) {
        removeByIdTE(parameter.getTrId());
    }

    @Override
    public IPage<TenantRoleDTO> getPage(TenantRolePageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), TenantRoleDTO.class,
                new MTLambdaWrapper<TenantRole>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getTrIds()), TenantRole::getTrId, parameter.getTrIds())
        );
    }

    @Override
    public void save(TenantRoleSaveParameter parameter) {
        TenantRole tenantRole = new TenantRole();
        BeanUtils.copyProperties(parameter, tenantRole);
        saveTE(tenantRole);
        parameter.setTrId(tenantRole.getTrId());
    }

    @Override
    public void update(TenantRoleUpdateParameter parameter) {
        TenantRole tenantRole = new TenantRole();
        BeanUtils.copyProperties(parameter, tenantRole);
        updateByIdTE(tenantRole);
    }

    @Override
    public List<String> getRoleNames(Long tenantId) {
        List<TenantRoleDTO> dtoList = baseMapper.selectJoinList(TenantRoleDTO.class,
                new MPJLambdaWrapper<TenantRole>()
                        .selectAs(Role::getAlias, TenantRoleDTO::getRoleName)
                        .leftJoin(Role.class, Role::getRoleId, TenantRole::getRoleId)
                        .eq(TenantRole::getTenantId, tenantId)
                        .eq(Role::getOwner, ApplicationType.MERCHANT.getValue())
        );
        if(dtoList!= null && dtoList.size() > 0) {
            return dtoList.stream().map(m->m.getRoleName()).collect(Collectors.toList());
        } else{
            return cn.hutool.core.collection.ListUtil.empty();
        }
    }

    @Override
    public String getRoleAlias(Long tenantId) {
        return baseMapper.selectJoinOne(String.class,
                new MPJLambdaWrapper<TenantRole>()
                        .select(Role::getAlias)
                        .leftJoin(Role.class, Role::getRoleId, TenantRole::getRoleId)
                        .eq(TenantRole::getTenantId, tenantId)
                        .eq(Role::getOwner, ApplicationType.MERCHANT.getValue())
        );
    }

    @Override
    public List<Role> getRoles(Long tenantId) {
        return baseMapper.selectJoinList(Role.class,
                new MPJLambdaWrapper<TenantRole>()
                        .selectAll(Role.class)
                        .innerJoin(Role.class,
                                on-> on.eq(Role::getRoleId, TenantRole::getRoleId)
                                        .eq(Role::getOwner, ApplicationType.MERCHANT.getValue())
                                        .eq(Role::getStatus, StatusEnum.NORMAL.getCode())
                        )
                        .eq(TenantRole::getTenantId, tenantId)
        );
    }

    @Override
    public boolean change(Long tenantId, Long roleId) {
        baseMapper.delete(new LambdaQueryWrapper<TenantRole>()
                .eq(TenantRole::getTenantId, tenantId));
        TenantRole tenantRole = new TenantRole();
        tenantRole.setTenantId(tenantId);
        tenantRole.setRoleId(roleId);
        return baseMapper.insert(tenantRole) == 1;
    }

    @Override
    public boolean setRoles(Long tenantId, List<Long> roleIds) {
        baseMapper.delete(new LambdaQueryWrapper<TenantRole>()
                .eq(TenantRole::getTenantId, tenantId));
        List<TenantRole> roles = new ArrayList<>();
        for (Long roleId : roleIds) {
            TenantRole role = new TenantRole();
            role.setTenantId(tenantId);
            role.setRoleId(roleId);
            roles.add(role);
        }
        return saveBatch(roles);
    }
}
