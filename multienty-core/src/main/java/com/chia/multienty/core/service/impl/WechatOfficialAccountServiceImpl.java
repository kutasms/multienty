package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.dto.WechatOfficialAccountDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.WechatOfficialAccountMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.WechatOfficialAccount;
import com.chia.multienty.core.service.WechatOfficialAccountService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatOfficialAccountDisableParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 微信公众号账户 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@Service
public class WechatOfficialAccountServiceImpl extends KutaBaseServiceImpl<WechatOfficialAccountMapper, WechatOfficialAccount> implements WechatOfficialAccountService {


    @Override
    public WechatOfficialAccountDTO getDetail(WechatOfficialAccountDetailGetParameter parameter) {
        return selectJoinOne(WechatOfficialAccountDTO.class,
                        MPJWrappers.<WechatOfficialAccount>lambdaJoin().eq(WechatOfficialAccount::getWoaId, parameter.getWoaId()));
    }

    @Override
    public void delete(WechatOfficialAccountDeleteParameter parameter) {
        removeByIdTE(parameter.getWoaId());
    }

    @Override
    public IPage<WechatOfficialAccountDTO> getPage(WechatOfficialAccountPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatOfficialAccountDTO.class,
                new MTLambdaWrapper<WechatOfficialAccount>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getWoaIds()), WechatOfficialAccount::getWoaId, parameter.getWoaIds())
        );
    }

    @Override
    public void save(WechatOfficialAccountSaveParameter parameter) {
        WechatOfficialAccount wechatOfficialAccount = new WechatOfficialAccount();
        BeanUtils.copyProperties(parameter, wechatOfficialAccount);
        saveTE(wechatOfficialAccount);
        parameter.setWoaId(wechatOfficialAccount.getWoaId());
    }

    @Override
    public void update(WechatOfficialAccountUpdateParameter parameter) {
        WechatOfficialAccount wechatOfficialAccount = new WechatOfficialAccount();
        BeanUtils.copyProperties(parameter, wechatOfficialAccount);
        updateByIdTE(wechatOfficialAccount);
    }
    @Override
    public void enable(WechatOfficialAccountEnableParameter parameter) {
        WechatOfficialAccount wechatOfficialAccount = new WechatOfficialAccount();
        BeanUtils.copyProperties(parameter, wechatOfficialAccount);
        wechatOfficialAccount.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(wechatOfficialAccount);
    }

    @Override
    public void disable(WechatOfficialAccountDisableParameter parameter) {
        WechatOfficialAccount wechatOfficialAccount = new WechatOfficialAccount();
        BeanUtils.copyProperties(parameter, wechatOfficialAccount);
        wechatOfficialAccount.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(wechatOfficialAccount);
    }
}
