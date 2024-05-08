package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.WechatBetaMppRegisterAudit;
import com.chia.multienty.core.mapper.WechatBetaMppRegisterAuditMapper;
import com.chia.multienty.core.service.WechatBetaMppRegisterAuditService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.WechatBetaMppRegisterAuditDTO;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppRegisterAuditDisableParameter;
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
 * 微信试用小程序注册审核单 服务实现类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
@Service
@RequiredArgsConstructor
@DS(MultientyConstants.DS_SHARDING)
public class WechatBetaMppRegisterAuditServiceImpl extends KutaBaseServiceImpl<WechatBetaMppRegisterAuditMapper, WechatBetaMppRegisterAudit> implements WechatBetaMppRegisterAuditService {


    @Override
    public WechatBetaMppRegisterAuditDTO getDetail(WechatBetaMppRegisterAuditDetailGetParameter parameter) {
        return selectJoinOne(WechatBetaMppRegisterAuditDTO.class,
                        MPJWrappers.<WechatBetaMppRegisterAudit>lambdaJoin()
                        .eq(WechatBetaMppRegisterAudit::getTenantId, parameter.getTenantId())
                        .eq(WechatBetaMppRegisterAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public void update(WechatBetaMppRegisterAuditUpdateParameter parameter) {
        WechatBetaMppRegisterAudit wechatBetaMppRegisterAudit = new WechatBetaMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatBetaMppRegisterAudit);
        update(wechatBetaMppRegisterAudit, new LambdaQueryWrapper<WechatBetaMppRegisterAudit>()
                .eq(WechatBetaMppRegisterAudit::getTenantId, parameter.getTenantId())
                .eq(WechatBetaMppRegisterAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public void delete(WechatBetaMppRegisterAuditDeleteParameter parameter) {
        removeTE(new LambdaQueryWrapper<WechatBetaMppRegisterAudit>()
                .eq(WechatBetaMppRegisterAudit::getTenantId, parameter.getTenantId())
                .eq(WechatBetaMppRegisterAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public IPage<WechatBetaMppRegisterAuditDTO> getPage(WechatBetaMppRegisterAuditPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatBetaMppRegisterAuditDTO.class,
                new MTLambdaWrapper<WechatBetaMppRegisterAudit>()
                        .solveGenericParameters(parameter)
                        .eq(WechatBetaMppRegisterAudit::getTenantId, parameter.getTenantId())
                        .in(!ListUtil.isEmpty(parameter.getAuditIds()),
                                WechatBetaMppRegisterAudit::getAuditId,
                                parameter.getAuditIds())
        );
    }

    @Override
    public void save(WechatBetaMppRegisterAuditSaveParameter parameter) {
        WechatBetaMppRegisterAudit wechatBetaMppRegisterAudit = new WechatBetaMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatBetaMppRegisterAudit);
        wechatBetaMppRegisterAudit.setAuditId(IdWorkerProvider.next());

        wechatBetaMppRegisterAudit.setTenantId(MultientyContext.getTenant().getTenantId());
        saveTE(wechatBetaMppRegisterAudit);
        parameter.setAuditId(wechatBetaMppRegisterAudit.getAuditId());
    }


    @Override
    public void enable(WechatBetaMppRegisterAuditEnableParameter parameter) {
        WechatBetaMppRegisterAudit wechatBetaMppRegisterAudit = new WechatBetaMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatBetaMppRegisterAudit);
        wechatBetaMppRegisterAudit.setStatus(StatusEnum.NORMAL.getCode());
        update(wechatBetaMppRegisterAudit, new LambdaQueryWrapper<WechatBetaMppRegisterAudit>()
                .eq(WechatBetaMppRegisterAudit::getTenantId, parameter.getTenantId())
                .eq(WechatBetaMppRegisterAudit::getAuditId, parameter.getAuditId()));

    }

    @Override
    public void disable(WechatBetaMppRegisterAuditDisableParameter parameter) {
        WechatBetaMppRegisterAudit wechatBetaMppRegisterAudit = new WechatBetaMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatBetaMppRegisterAudit);
        wechatBetaMppRegisterAudit.setStatus(StatusEnum.DISABLED.getCode());
        update(wechatBetaMppRegisterAudit, new LambdaQueryWrapper<WechatBetaMppRegisterAudit>()
                .eq(WechatBetaMppRegisterAudit::getTenantId, parameter.getTenantId())
                .eq(WechatBetaMppRegisterAudit::getAuditId, parameter.getAuditId()));
    }
}
