package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.domain.dto.TenantSubAccountDTO;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.mapper.TenantSubAccountMapper;
import com.chia.multienty.core.mybatis.KutaFuncEnum;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.parameter.user.FuncPermissionListGetParameter;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.TenantSubAccount;
import com.chia.multienty.core.pojo.TenantSubAccountRole;
import com.chia.multienty.core.service.PermissionService;
import com.chia.multienty.core.service.TenantSubAccountRoleService;
import com.chia.multienty.core.service.TenantSubAccountService;
import com.chia.multienty.core.tools.IdWorkerProvider;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.core.util.MD5Util;
import com.github.yulichang.toolkit.MPJWrappers;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 余额账单 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */
@Service
@RequiredArgsConstructor
public class TenantSubAccountServiceImpl extends KutaBaseServiceImpl<TenantSubAccountMapper, TenantSubAccount> implements TenantSubAccountService {


    private final TenantSubAccountRoleService tenantSubAccountRoleService;

    private final PermissionService permissionService;


    @Override
    public LoggedUserVO getLoggedUser(Long userId) {
        TenantSubAccount account = getOne(mtLambdaWrapper().eq(TenantSubAccount::getSubAccountId, userId));
        List<Role> roles = tenantSubAccountRoleService.getRoles(account.getSubAccountId());
        return LoggedUserVO
                .builder()
                .username(account.getName())
                .avatar(MultientyConstants.DEFAULT_AVATAR)
                .permissions(permissionService
                        .getFuncPermissions(
                                new FuncPermissionListGetParameter()
                                        .setRoles(roles)
                                        .setOwner(ApplicationType.MERCHANT.getValue())
                        )
                )
                .roles(roles.stream().map(m->m.getName()).collect(Collectors.toList()))
                .superAdmin(false)
                .build();
    }

    @Override
    public TenantSubAccountDTO getDetail(TenantSubAccountDetailGetParameter parameter) {
        return selectJoinOne(TenantSubAccountDTO.class,
                        MPJWrappers.<TenantSubAccount>lambdaJoin().eq(TenantSubAccount::getSubAccountId, parameter.getSubAccountId()));
    }

    @Override
    public void delete(TenantSubAccountDeleteParameter parameter) {
        removeByIdTE(parameter.getSubAccountId());
    }

    @Override
    public IPage<TenantSubAccountDTO> getPage(TenantSubAccountPageGetParameter parameter) {
        Page<TenantSubAccountDTO> page = selectJoinListPage(parameter.getPageObj(), TenantSubAccountDTO.class,
                new MTLambdaWrapper<TenantSubAccount>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getSubAccountIds()), TenantSubAccount::getSubAccountId, parameter.getSubAccountIds())
        );
        if(page.getRecords().size()>0) {
            List<TenantSubAccountRole> roles = tenantSubAccountRoleService.findRoles(page.getRecords()
                    .stream()
                    .map(m -> m.getSubAccountId())
                    .collect(Collectors.toList()));
            page.getRecords().forEach(sub-> {
                sub.setRoleIds(roles
                        .stream()
                        .filter(p-> p.getSubAccountId().equals(sub.getSubAccountId()))
                        .map(m->m.getRoleId())
                        .collect(Collectors.toList()));
            });
        }
        return page;
    }

    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,
            rollbackFor = {Exception.class})
    public void save(TenantSubAccountSaveParameter parameter) {
        TenantSubAccount tenantSubAccount = new TenantSubAccount();
        BeanUtils.copyProperties(parameter, tenantSubAccount);
        tenantSubAccount.setSubAccountId(IdWorkerProvider.next());
        tenantSubAccount.setPassword(MD5Util.md5WithSalt(parameter.getPassword()));
        saveTE(tenantSubAccount);
        if(CollectionUtils.isNotEmpty(parameter.getRoleIds())) {
            tenantSubAccountRoleService.setRoles(tenantSubAccount.getSubAccountId(),parameter.getRoleIds());
        }
        parameter.setSubAccountId(tenantSubAccount.getSubAccountId());
    }

    @Override
    public void update(TenantSubAccountUpdateParameter parameter) {
        TenantSubAccount tenantSubAccount = new TenantSubAccount();
        BeanUtils.copyProperties(parameter, tenantSubAccount);
        if(tenantSubAccount.getPassword()!=null && tenantSubAccount.getPassword().length() < 40) {
            tenantSubAccount.setPassword(MD5Util.md5WithSalt(parameter.getPassword()));
        }
        if(CollectionUtils.isNotEmpty(parameter.getRoleIds())) {
            tenantSubAccountRoleService.setRoles(tenantSubAccount.getSubAccountId(),parameter.getRoleIds());
        }
        updateByIdTE(tenantSubAccount);
    }
    @Override
    public void enable(TenantSubAccountEnableParameter parameter) {
        TenantSubAccount tenantSubAccount = new TenantSubAccount();
        BeanUtils.copyProperties(parameter, tenantSubAccount);
        tenantSubAccount.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(tenantSubAccount);
    }

    @Override
    public void disable(TenantSubAccountDisableParameter parameter) {
        TenantSubAccount tenantSubAccount = new TenantSubAccount();
        BeanUtils.copyProperties(parameter, tenantSubAccount);
        tenantSubAccount.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(tenantSubAccount);
    }

    @Override
    public UserDetails findByUsername(String username) {
        TenantSubAccountDTO tenant = selectJoinOne(
                TenantSubAccountDTO.class,
                new MTLambdaWrapper<TenantSubAccount>()
                        .selectAll(TenantSubAccount.class)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT, Role::getName, TenantSubAccountDTO::getRoleName)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT,Role::getAlias, TenantSubAccountDTO::getRoleAlias)
                        .leftJoin(TenantSubAccountRole.class, TenantSubAccountRole::getSubAccountId,
                                TenantSubAccount::getSubAccountId)
                        .leftJoin(Role.class, Role::getRoleId,
                                TenantSubAccountRole::getRoleId)
                        .and(and-> and.eq(TenantSubAccount::getName, username).or(or->or.eq(TenantSubAccount::getPhoneNumber, username)))
        );

        if(tenant == null) {
            throw new KutaRuntimeException(HttpResultEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        List<Role> roles = tenantSubAccountRoleService.getRoles(tenant.getTenantId());
        if(roles.size() > 0) {
            tenant.setPermissions(permissionService.getFuncPermissions(
                    new FuncPermissionListGetParameter()
                            .setOwner(ApplicationType.PLATFORM.getValue())
                            .setRoles(roles)
            ));
        }
        tenant.setIsSuperAdmin(false);
        LoggedUserVO loggedUser = new LoggedUserVO(tenant);
        loggedUser.setApplicationType(ApplicationType.MERCHANT);
        loggedUser.setAvatar(MultientyConstants.DEFAULT_AVATAR);
        return loggedUser;
    }
}
