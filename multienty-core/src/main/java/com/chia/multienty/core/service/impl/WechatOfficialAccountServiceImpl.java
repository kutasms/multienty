package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.WechatOfficialAccount;
import com.chia.multienty.core.mapper.WechatOfficialAccountMapper;
import com.chia.multienty.core.service.WechatOfficialAccountService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.WechatOfficialAccountDTO;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDisableParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chia.multienty.core.tools.MultientyContext;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.chia.multienty.core.tools.IdWorkerProvider;
/**
 * <p>
 * 微信公众号账户 服务实现类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */
@Service
@RequiredArgsConstructor
@DS(MultientyConstants.DS_SHARDING)
public class WechatOfficialAccountServiceImpl extends KutaBaseServiceImpl<WechatOfficialAccountMapper, WechatOfficialAccount> implements WechatOfficialAccountService {


    @Override
    public WechatOfficialAccountDTO getDetail(WechatOfficialAccountDetailGetParameter parameter) {
        return selectJoinOne(WechatOfficialAccountDTO.class,
                        MPJWrappers.<WechatOfficialAccount>lambdaJoin()
                        .eq(WechatOfficialAccount::getTenantId, parameter.getTenantId())
                        .eq(WechatOfficialAccount::getWoaId, parameter.getWoaId()));
    }

    @Override
    public void update(WechatOfficialAccountUpdateParameter parameter) {
        WechatOfficialAccount wechatOfficialAccount = new WechatOfficialAccount();
        BeanUtils.copyProperties(parameter, wechatOfficialAccount);
        update(wechatOfficialAccount, new LambdaQueryWrapper<WechatOfficialAccount>()
                .eq(WechatOfficialAccount::getTenantId, parameter.getTenantId())
                .eq(WechatOfficialAccount::getWoaId, parameter.getWoaId()));
    }

    @Override
    public void delete(WechatOfficialAccountDeleteParameter parameter) {
        removeTE(new LambdaQueryWrapper<WechatOfficialAccount>()
                .eq(WechatOfficialAccount::getTenantId, parameter.getTenantId())
                .eq(WechatOfficialAccount::getWoaId, parameter.getWoaId()));
    }

    @Override
    public IPage<WechatOfficialAccountDTO> getPage(WechatOfficialAccountPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatOfficialAccountDTO.class,
                new MTLambdaWrapper<WechatOfficialAccount>()
                        .solveGenericParameters(parameter)
                        .eq(WechatOfficialAccount::getTenantId, parameter.getTenantId())
                        .in(!ListUtil.isEmpty(parameter.getWoaIds()),
                                WechatOfficialAccount::getWoaId,
                                parameter.getWoaIds())
        );
    }

    @Override
    public void save(WechatOfficialAccountSaveParameter parameter) {
        WechatOfficialAccount wechatOfficialAccount = new WechatOfficialAccount();
        BeanUtils.copyProperties(parameter, wechatOfficialAccount);
        wechatOfficialAccount.setWoaId(IdWorkerProvider.next());

        wechatOfficialAccount.setTenantId(MultientyContext.getTenant().getTenantId());
        saveTE(wechatOfficialAccount);
        parameter.setWoaId(wechatOfficialAccount.getWoaId());
    }


    @Override
    public void enable(WechatOfficialAccountEnableParameter parameter) {
        WechatOfficialAccount wechatOfficialAccount = new WechatOfficialAccount();
        BeanUtils.copyProperties(parameter, wechatOfficialAccount);
        wechatOfficialAccount.setStatus(StatusEnum.NORMAL.getCode());
        update(wechatOfficialAccount, new LambdaQueryWrapper<WechatOfficialAccount>()
                .eq(WechatOfficialAccount::getTenantId, parameter.getTenantId())
                .eq(WechatOfficialAccount::getWoaId, parameter.getWoaId()));

    }

    @Override
    public void disable(WechatOfficialAccountDisableParameter parameter) {
        WechatOfficialAccount wechatOfficialAccount = new WechatOfficialAccount();
        BeanUtils.copyProperties(parameter, wechatOfficialAccount);
        wechatOfficialAccount.setStatus(StatusEnum.DISABLED.getCode());
        update(wechatOfficialAccount, new LambdaQueryWrapper<WechatOfficialAccount>()
                .eq(WechatOfficialAccount::getTenantId, parameter.getTenantId())
                .eq(WechatOfficialAccount::getWoaId, parameter.getWoaId()));
    }
}
