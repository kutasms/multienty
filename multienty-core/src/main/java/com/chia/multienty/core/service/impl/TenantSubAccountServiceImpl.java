package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.dto.TenantSubAccountDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.TenantSubAccountMapper;
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.TenantSubAccount;
import com.chia.multienty.core.service.TenantSubAccountService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.user.TenantSubAccountDetailGetParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountPageGetParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountDeleteParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountSaveParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountUpdateParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountEnableParameter;
import com.chia.multienty.core.parameter.user.TenantSubAccountDisableParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 余额账单 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-02-01
 */
@Service
public class TenantSubAccountServiceImpl extends KutaBaseServiceImpl<TenantSubAccountMapper, TenantSubAccount> implements TenantSubAccountService {


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
        return selectJoinListPage(parameter.getPageObj(), TenantSubAccountDTO.class,
                new KutaLambdaWrapper<TenantSubAccount>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getSubAccountIds()), TenantSubAccount::getSubAccountId, parameter.getSubAccountIds())
        );
    }

    @Override
    public void save(TenantSubAccountSaveParameter parameter) {
        TenantSubAccount tenantSubAccount = new TenantSubAccount();
        BeanUtils.copyProperties(parameter, tenantSubAccount);
        saveTE(tenantSubAccount);
        parameter.setSubAccountId(tenantSubAccount.getSubAccountId());
    }

    @Override
    public void update(TenantSubAccountUpdateParameter parameter) {
        TenantSubAccount tenantSubAccount = new TenantSubAccount();
        BeanUtils.copyProperties(parameter, tenantSubAccount);
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
}
