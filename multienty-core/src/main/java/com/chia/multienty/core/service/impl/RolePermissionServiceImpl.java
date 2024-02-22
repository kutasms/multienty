package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.RolePermissionDTO;
import com.chia.multienty.core.mapper.RolePermissionMapper;
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.pojo.RolePermission;
import com.chia.multienty.core.service.RolePermissionService;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 账户角色关联权限实体 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@Service
public class RolePermissionServiceImpl extends KutaBaseServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {


    @Override
    public RolePermissionDTO getDetail(RolePermissionDetailGetParameter parameter) {
        return selectJoinOne(RolePermissionDTO.class,
                        MPJWrappers.<RolePermission>lambdaJoin().eq(RolePermission::getRpId, parameter.getRpId()));
    }

    @Override
    public void delete(RolePermissionDeleteParameter parameter) {
        removeByIdTE(parameter.getRpId());
    }

    @Override
    public IPage<RolePermissionDTO> getPage(RolePermissionPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), RolePermissionDTO.class,
                new KutaLambdaWrapper<RolePermission>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getRpIds()), RolePermission::getRpId, parameter.getRpIds())
        );
    }

    @Override
    public void save(RolePermissionSaveParameter parameter) {
        RolePermission rolePermission = new RolePermission();
        BeanUtils.copyProperties(parameter, rolePermission);
        saveTE(rolePermission);
        parameter.setRpId(rolePermission.getRpId());
    }

    @Override
    public void update(RolePermissionUpdateParameter parameter) {
        RolePermission rolePermission = new RolePermission();
        BeanUtils.copyProperties(parameter, rolePermission);
        updateByIdTE(rolePermission);
    }

    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = { java.lang.Exception.class})
    public boolean changePermissions(RolePermissionChangeParameter parameter) {
        baseMapper.delete(new LambdaQueryWrapper<RolePermission>().eq(RolePermission::getRoleId, parameter.getRoleId()));
        List<RolePermission> rolePermissions = parameter.getPermissionIds().stream().map(m -> {
            return new RolePermission().setRoleId(parameter.getRoleId()).setPermissionId(m);
        }).collect(Collectors.toList());
        return saveBatch(rolePermissions);
    }
}
