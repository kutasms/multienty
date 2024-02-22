package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.RoleDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.RoleMapper;
import com.chia.multienty.core.mybatis.KutaFuncEnum;
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.RolePermission;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.user.RoleListGetParameter;
import com.chia.multienty.core.parameter.user.RoleSaveParameter;
import com.chia.multienty.core.parameter.user.RoleUpdateParameter;
import com.chia.multienty.core.service.RoleService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>
 * 角色信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Service
public class RoleServiceImpl extends KutaBaseServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public boolean deleteSafely(Long roleId) throws KutaRuntimeException {
        Role role = getBy(roleId,
                Role::getStatus,
                Role::getVersion,
                Role::getRoleId,
                Role::getDeletable);
        if(!role.getDeletable()) {
            throw new KutaRuntimeException(HttpResultEnum.ROLE_CANNOT_BE_DELETED);
        }
        role.setStatus(StatusEnum.DELETED.getCode());
        return baseMapper.updateById(role) == 1;
    }

    @Override
    public IPage<RoleDTO> getList(RoleListGetParameter parameter) {
        IPage<RoleDTO> page = baseMapper.selectJoinPage(parameter.getPageObj(),
                RoleDTO.class,
                new KutaLambdaWrapper<Role>()
                        .selectAll(Role.class)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT, RolePermission::getPermissionId, RoleDTO::getPermissionJoinedStr)
                        .leftJoin(RolePermission.class,RolePermission::getRoleId, Role::getRoleId)
                        .gt(!ArrayUtils.isEmpty(parameter.getCreateTimeDuration()) && parameter.getCreateTimeDuration()[0] != null,
                                Role::getCreateTime, parameter.getCreateTimeDuration()[0])
                        .lt(!ArrayUtils.isEmpty(parameter.getCreateTimeDuration()) && parameter.getCreateTimeDuration()[1]!=null,
                                Role::getCreateTime, parameter.getCreateTimeDuration()[1])
                        .like(!StringUtils.isBlank(parameter.getKeywords()), Role::getName, parameter.getKeywords())
                        .eq(!StringUtils.isEmpty(parameter.getStatus()), Role::getStatus, parameter.getStatus())
                        .in(!ListUtil.isEmpty(parameter.getStatusList()),Role::getStatus, parameter.getStatusList())
                        .notIn(Role::getStatus, StatusEnum.DELETED.getCode())
                        .in(Role::getOwner, parameter.getOwner())
                        .orderByDesc(parameter.getOrderByDescColumns() != null, parameter.getOrderByDescColumns())
                        .orderByAsc(parameter.getOrderByAscColumns()!=null, parameter.getOrderByAscColumns())
                        .groupBy(Role::getRoleId)
        );
        page.getRecords().forEach(m-> {
            if(m.getPermissionJoinedStr()!=null) {
                m.setPermissionIds(Arrays.asList(m.getPermissionJoinedStr().split(",")));
            } else{
                m.setPermissionIds(new ArrayList<>());
            }
        });
        return page;
    }
    @Override
    public int save(RoleSaveParameter parameter) {
        Role role = new Role();
        BeanUtils.copyProperties(parameter, role);
        role.setCreateTime(LocalDateTime.now());
        role.setStatus(StatusEnum.NORMAL.getCode());
        role.setVersion(1L);
        //检测角色对象
        int result = baseMapper.insert(role);
        parameter.setRoleId(role.getRoleId());
        return result;
    }

    @Override
    public int update(RoleUpdateParameter parameter) throws KutaRuntimeException {
        Role origRole = getBy(parameter.getRoleId(), Role::getEditable, Role::getVersion);
        if(!origRole.getEditable()) {
            throw new KutaRuntimeException(HttpResultEnum.ROLE_NON_EDITABLE);
        }
        Role role = new Role();
        BeanUtils.copyProperties(parameter, role);
        role.setVersion(origRole.getVersion());
        role.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(role);
    }




    @Override
    public boolean disable(Long roleId, Long version) {
        return setStatus(roleId, version, StatusEnum.DISABLED);
    }

    @Override
    public boolean disable(Long roleId) {
        Long version = getBy(roleId, Role::getVersion).getVersion();
        return disable(roleId, version);
    }


    private boolean setStatus(Long roleId, Long version, StatusEnum status) {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setVersion(version);
        role.setStatus(status.getCode());
        role.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(role) == 1;
    }


    @Override
    public boolean enable(Long roleId, Long version) {
        return setStatus(roleId, version, StatusEnum.NORMAL);
    }
    @Override
    public boolean enable(Long roleId) {
        Long version = getBy(roleId, Role::getVersion).getVersion();
        return enable(roleId, version);
    }
}
