package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.PermissionDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.domain.enums.SymbolEnum;
import com.chia.multienty.core.domain.vo.permission.PermissionType;
import com.chia.multienty.core.domain.vo.permission.PermissionVO;
import com.chia.multienty.core.domain.vo.permission.VueRouteMeta;
import com.chia.multienty.core.mapper.PermissionMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.constants.LikeMode;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.pojo.Permission;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.RolePermission;
import com.chia.multienty.core.pojo.UserRole;
import com.chia.multienty.core.service.PermissionService;
import com.chia.multienty.core.service.RolePermissionService;
import com.chia.multienty.core.service.UserRoleService;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限菜单信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-10
 */
@Service
public class PermissionServiceImpl extends KutaBaseServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private UserRoleService kutaUserRoleService;

    @Autowired
    private RolePermissionService kutaRolePermissionService;

    @Override
    public List<PermissionDTO> getUserPermissions(Long userId, Long owner) {
        List<PermissionDTO> permissions = kutaUserRoleService.selectJoinList(PermissionDTO.class,
                new MPJLambdaWrapper<UserRole>()
                        .selectAll(Permission.class)
                        .selectAs(Role::getName, PermissionDTO::getRoleName)
                        .innerJoin(RolePermission.class, RolePermission::getRoleId, UserRole::getRoleId)
                        .innerJoin(Role.class, Role::getRoleId, RolePermission::getRoleId)
                        .innerJoin(RolePermission.class, on ->
                                on.eq(Permission::getOwner, owner)
                                        .eq(Permission::getPermissionId, RolePermission::getPermissionId))
                        .and(on ->
                                on.eq(UserRole::getUserId, userId)
                                        .or().eq(Role::getSuperAdmin, true)
                        )
                        .eq(Permission::getType, PermissionType.MENU.getCode())
                        .eq(Permission::getOwner, 1L)
                        .in(Permission::getStatus, StatusEnum.NORMAL.getCode())
                        .orderByAsc(Permission::getSort)
                        .groupBy(Permission::getPermissionId)
        );
        return permissions;
    }

    @Override
    public IPage<PermissionDTO> getPlatformTopPermissions(TopPermissionListGetParameter parameter) {
        return baseMapper.selectJoinPage(parameter.getPageObj(),
                PermissionDTO.class,
                new MPJLambdaWrapper<Permission>()
                        .selectAll(Permission.class)
                        .eq(Permission::getOwner, parameter.getOwner())
                        .eq(Permission::getPid, 0L)
                        .eq(Permission::getOwner, 1L)
                        .notIn(Permission::getStatus, StatusEnum.DELETED.getCode())
                        .orderByAsc(Permission::getSort)
        );
    }

    @Override
    public List<PermissionDTO> getChildren(List<String> pidList) {
        return baseMapper.selectJoinList(PermissionDTO.class,
                new MTLambdaWrapper<Permission>()
                        .likeList(pidList, Permission::getHierarchy, LikeMode.RIGHT)
                        .notIn(Permission::getStatus, StatusEnum.DELETED.getCode())
        );
    }

    @Override
    public IPage<PermissionDTO> getFormattedPlatformTopPermissions(TopPermissionListGetParameter parameter) {
        IPage<PermissionDTO> tops = getPlatformTopPermissions(parameter);
        if(tops.getRecords().size() > 0) {
            List<PermissionDTO> children = getChildren(tops
                    .getRecords()
                    .stream()
                    .map(m -> SymbolEnum.SLASH.getCode() + m.getPermissionId().toString())
                    .collect(Collectors.toList()));
            tops.getRecords().forEach(top -> {
                top.setChildren(getFormattedDTO(top.getPermissionId(), children));
            });
        }
        return tops;
    }

    @Override
    public List<PermissionDTO> getFormattedDTO(Long pid, List<PermissionDTO> permissions) {
        List<PermissionDTO> children =
                pid == null
                        ? permissions
                        : permissions
                        .stream()
                        .filter(p -> p.getPid().equals(pid))
                        .collect(Collectors.toList());
        if(children.size() > 0) {
            children.forEach(child -> {
                child.setChildren(getFormattedDTO(child.getPermissionId(), permissions));
            });
        }
        return children;
    }

    @Override
    public List<PermissionVO> getFormattedVO(List<PermissionDTO> permissions) {
        if(permissions == null || permissions.size() == 0) {
            return new ArrayList<>();
        }
        return buildVO(null, permissions);
    }


    private List<PermissionVO> buildVO(Long pid, List<PermissionDTO> permissions) {
        List<PermissionDTO> children =
                pid == null
                        ? permissions
                        .stream()
                        .filter(p->p.getPid().equals(0L))
                        .collect(Collectors.toList())
                        : permissions
                        .stream()
                        .filter(p -> p.getPid().equals(pid))
                        .collect(Collectors.toList());
        if(children.size() > 0) {
            return children.stream().map(item -> PermissionVO.builder()
                    .caseSensitive(false)
                    .alias(item.getAlias())
                    .name(item.getName())
                    .hidden(item.getHidden())
                    .pid(item.getPid())
                    .path(item.getPath())
                    .redirect(item.getRedirect())
                    .meta(VueRouteMeta
                            .builder()
                            .affix(item.getAffix())
                            .icon(item.getIcon())
                            .title(item.getName())
                            .permissionId(item.getPermissionId())
                            .type(PermissionType.valueOf(item.getType().intValue()))
                            .permissions(permissions
                                    .stream()
                                    .filter(p ->
                                            p.getPid().equals(item.getPermissionId())
                                                    && p.getType().equals(PermissionType.INTERACTIVE.getCode())
                                    ).map(m -> m.getComponent()).collect(Collectors.toList()))

                            .build()
                    )
                    .component(item.getComponent())
                    .children(buildVO(item.getPermissionId(), permissions))
                    .build()
            ).collect(Collectors.toList());
        } else {
            return cn.hutool.core.collection.ListUtil.<PermissionVO>empty();
        }
    }

    @Override
    public int save(PermissionSaveParameter parameter) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(parameter, permission);
        Permission parent = null;
        if(parameter.getPid() != null && parameter.getPid() > 0) {
            parent = baseMapper.selectById(parameter.getPid());
            permission.setHierarchy(parent.getPid().equals(0L) ?
                    String.format("%s%s", SymbolEnum.SLASH.getCode(), parent.getPermissionId())
                    : String.format("%s%s%s", parent.getHierarchy(), SymbolEnum.SLASH.getCode(), parent.getPermissionId()));
        } else {
            permission.setHierarchy(SymbolEnum.SLASH.getCode());
        }
        permission.setVersion(1L);
        permission.setStatus(StatusEnum.NORMAL.getCode());
        permission.setCreateTime(LocalDateTime.now());
        permission.setOwner(1L);
        int result = baseMapper.insert(permission);
        parameter.setPermissionId(permission.getPermissionId());
        return result;
    }

    @Override
    public int update(PermissionUpdateParameter parameter) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(parameter, permission);
        permission.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(permission);
    }


    private boolean setStatus(Long permissionId, Long version, StatusEnum status) {
        Permission permission = new Permission();
        permission.setPermissionId(permissionId);
        permission.setVersion(version);
        permission.setStatus(status.getCode());
        permission.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(permission) == 1;
    }

    @Override
    public boolean disable(Long permissionId, Long version) {
        return setStatus(permissionId, version, StatusEnum.DISABLED);
    }
    @Override
    public boolean disable(Long permissionId) {
        Long version = getBy(permissionId, Permission::getVersion).getVersion();
        return disable(permissionId, version);
    }

    @Override
    public boolean enable(Long permissionId, Long version) {
        return setStatus(permissionId, version, StatusEnum.NORMAL);
    }
    @Override
    public boolean enable(Long permissionId) {
        Long version = getBy(permissionId, Permission::getVersion).getVersion();
        return enable(permissionId, version);
    }
    @Override
    public List<PermissionDTO> getFuncPermissions(FuncPermissionListGetParameter parameter) {
        List<Role> roles = kutaUserRoleService
                .getRoles(parameter.getUserId());
        boolean isSuperAdmin = roles.stream()
                .filter(p->p.getSuperAdmin()!=null && p.getSuperAdmin()).findAny() != null;
        if(isSuperAdmin) {
            // 超管帐号
            return selectJoinList(PermissionDTO.class, new MTLambdaWrapper<Permission>()
                    .select(Permission::getPermissionId)
                    .select(Permission::getComponent)
                    .eq(Permission::getType, PermissionType.INTERACTIVE.getCode())
                    .eq(Permission::getOwner, parameter.getOwner())
                    .eq(Permission::getStatus, StatusEnum.NORMAL.getCode())
            );
        } else {
            return selectJoinList(PermissionDTO.class, new MTLambdaWrapper<Permission>()
                    .select(Permission::getPermissionId)
                    .select(Permission::getComponent)
                    .innerJoin(RolePermission.class,
                            on-> on.eq(RolePermission::getPermissionId,
                                            Permission::getPermissionId)
                                    .in(RolePermission::getRoleId,
                                            roles.stream().map(m->m.getRoleId()).collect(Collectors.toList()))
                    )
                    .eq(Permission::getType, PermissionType.INTERACTIVE.getCode())
                    .eq(Permission::getOwner, parameter.getOwner())
                    .eq(Permission::getStatus, StatusEnum.NORMAL.getCode())
            );
        }
    }

    @Override
    public List<PermissionDTO> getPermissions(PermissionListGetParameter parameter) {
        boolean isSuperAdmin = parameter.getRoles().stream()
                .filter(p->p.getSuperAdmin()!=null && p.getSuperAdmin()).findAny().isPresent();
        if(isSuperAdmin) {
            // 超管帐号
            return selectJoinList(PermissionDTO.class, new MTLambdaWrapper<Permission>()
                    .select(Permission::getPermissionId)
                    .select(Permission::getComponent)
                    .select(Permission::getApi)
                    .eq(Permission::getOwner, parameter.getOwner())
                    .eq(Permission::getStatus, StatusEnum.NORMAL.getCode())
            );
        } else {
            return selectJoinList(PermissionDTO.class, new MTLambdaWrapper<Permission>()
                    .select(Permission::getPermissionId)
                    .select(Permission::getComponent)
                    .select(Permission::getApi)
                    .select(Permission::getType)
                    .innerJoin(RolePermission.class,
                            on-> on.eq(RolePermission::getPermissionId,
                                            Permission::getPermissionId)
                                    .in(parameter.getRoles()!=null && parameter.getRoles().size() > 0,
                                            RolePermission::getRoleId,
                                            parameter.getRoles())
                    )
                    .eq(Permission::getOwner, parameter.getOwner())
                    .eq(Permission::getStatus, StatusEnum.NORMAL.getCode())
            );
        }
    }



    @Override
    public PermissionDTO getDetail(PermissionDetailGetParameter parameter) {
        return selectJoinOne(PermissionDTO.class,
                        MPJWrappers.<Permission>lambdaJoin().eq(Permission::getPermissionId, parameter.getPermissionId()));
    }

    @Override
    public void delete(PermissionDeleteParameter parameter) {
        removeByIdTE(parameter.getPermissionId());
    }

    @Override
    public IPage<PermissionDTO> getPage(PermissionPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), PermissionDTO.class,
                new MTLambdaWrapper<Permission>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getPermissionIds()), Permission::getPermissionId, parameter.getPermissionIds())
        );
    }


}
