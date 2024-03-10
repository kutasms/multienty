package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chia.multienty.core.domain.dto.UserRoleDTO;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.UserRoleMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.UserRole;
import com.chia.multienty.core.service.UserRoleService;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.user.UserRoleDetailGetParameter;
import com.chia.multienty.core.parameter.user.UserRolePageGetParameter;
import com.chia.multienty.core.parameter.user.UserRoleDeleteParameter;
import com.chia.multienty.core.parameter.user.UserRoleSaveParameter;
import com.chia.multienty.core.parameter.user.UserRoleUpdateParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 账户关联角色信息表 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@Service
public class UserRoleServiceImpl extends KutaBaseServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {


    @Override
    public UserRoleDTO getDetail(UserRoleDetailGetParameter parameter) {
        return selectJoinOne(UserRoleDTO.class,
                        MPJWrappers.<UserRole>lambdaJoin().eq(UserRole::getUrId, parameter.getUrId()));
    }

    @Override
    public void delete(UserRoleDeleteParameter parameter) {
        removeByIdTE(parameter.getUrId());
    }

    @Override
    public IPage<UserRoleDTO> getPage(UserRolePageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), UserRoleDTO.class,
                new MTLambdaWrapper<UserRole>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getUrIds()), UserRole::getUrId, parameter.getUrIds())
        );
    }

    @Override
    public void save(UserRoleSaveParameter parameter) {
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(parameter, userRole);
        saveTE(userRole);
        parameter.setUrId(userRole.getUrId());
    }

    @Override
    public void update(UserRoleUpdateParameter parameter) {
        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(parameter, userRole);
        updateByIdTE(userRole);
    }

    @Override
    public List<String> getRoleNames(Long userId) {
        List<UserRoleDTO> dtos = baseMapper.selectJoinList(UserRoleDTO.class,
                new MPJLambdaWrapper<UserRole>()
                        .selectAs(Role::getAlias, UserRoleDTO::getRoleName)
                        .leftJoin(Role.class, Role::getRoleId, UserRole::getRoleId)
                        .eq(UserRole::getUserId, userId)
                        .eq(Role::getOwner, ApplicationType.PLATFORM.getValue())
        );
        if(dtos!= null && dtos.size() > 0) {
            return dtos.stream().map(m->m.getRoleName()).collect(Collectors.toList());
        } else{
            return cn.hutool.core.collection.ListUtil.empty();
        }
    }

    @Override
    public String getRoleAlias(Long userId) {
        return baseMapper.selectJoinOne(String.class,
                new MPJLambdaWrapper<UserRole>()
                        .select(Role::getAlias)
                        .leftJoin(Role.class, Role::getRoleId, UserRole::getRoleId)
                        .eq(UserRole::getUserId, userId)
                        .eq(Role::getOwner, ApplicationType.PLATFORM.getValue())
        );
    }
    @Override
    public List<Role> getRoles(Long userId) {
        return baseMapper.selectJoinList(Role.class,
                new MPJLambdaWrapper<UserRole>()
                        .selectAll(Role.class)
                        .leftJoin(Role.class,
                                on-> on.eq(Role::getRoleId, UserRole::getRoleId)
                                        .eq(Role::getOwner, ApplicationType.PLATFORM.getValue())
                                        .eq(Role::getStatus, StatusEnum.NORMAL.getCode())
                        )
                        .eq(UserRole::getUserId, userId)
        );
    }
    @Override
    public boolean change(Long userId, Long roleId) {
        baseMapper.delete(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, userId));
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return baseMapper.insert(userRole) == 1;
    }

    @Override
    public boolean setRoles(Long userId, List<Long> roleIds) {
        baseMapper.delete(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, userId));
        List<UserRole> roles = new ArrayList<>();
        for (Long roleId : roleIds) {
            UserRole role = new UserRole();
            role.setUserId(userId);
            role.setRoleId(roleId);
            roles.add(role);
        }
        return saveBatch(roles);
    }
}
