package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.WechatBetaMppAuthAudit;
import com.chia.multienty.core.mapper.WechatBetaMppAuthAuditMapper;
import com.chia.multienty.core.service.WechatBetaMppAuthAuditService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.WechatBetaMppAuthAuditDTO;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatBetaMppAuthAuditDisableParameter;
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
 * 微信试用小程序转正审核单 服务实现类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
@Service
@RequiredArgsConstructor
@DS(MultientyConstants.DS_SHARDING)
public class WechatBetaMppAuthAuditServiceImpl extends KutaBaseServiceImpl<WechatBetaMppAuthAuditMapper, WechatBetaMppAuthAudit> implements WechatBetaMppAuthAuditService {


    @Override
    public WechatBetaMppAuthAuditDTO getDetail(WechatBetaMppAuthAuditDetailGetParameter parameter) {
        return selectJoinOne(WechatBetaMppAuthAuditDTO.class,
                        MPJWrappers.<WechatBetaMppAuthAudit>lambdaJoin()
                        .eq(WechatBetaMppAuthAudit::getTenantId, parameter.getTenantId())
                        .eq(WechatBetaMppAuthAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public void update(WechatBetaMppAuthAuditUpdateParameter parameter) {
        WechatBetaMppAuthAudit wechatBetaMppAuthAudit = new WechatBetaMppAuthAudit();
        BeanUtils.copyProperties(parameter, wechatBetaMppAuthAudit);
        update(wechatBetaMppAuthAudit, new LambdaQueryWrapper<WechatBetaMppAuthAudit>()
                .eq(WechatBetaMppAuthAudit::getTenantId, parameter.getTenantId())
                .eq(WechatBetaMppAuthAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public void delete(WechatBetaMppAuthAuditDeleteParameter parameter) {
        removeTE(new LambdaQueryWrapper<WechatBetaMppAuthAudit>()
                .eq(WechatBetaMppAuthAudit::getTenantId, parameter.getTenantId())
                .eq(WechatBetaMppAuthAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public IPage<WechatBetaMppAuthAuditDTO> getPage(WechatBetaMppAuthAuditPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatBetaMppAuthAuditDTO.class,
                new MTLambdaWrapper<WechatBetaMppAuthAudit>()
                        .solveGenericParameters(parameter)
                        .eq(WechatBetaMppAuthAudit::getTenantId, parameter.getTenantId())
                        .in(!ListUtil.isEmpty(parameter.getAuditIds()),
                                WechatBetaMppAuthAudit::getAuditId,
                                parameter.getAuditIds())
        );
    }

    @Override
    public void save(WechatBetaMppAuthAuditSaveParameter parameter) {
        WechatBetaMppAuthAudit wechatBetaMppAuthAudit = new WechatBetaMppAuthAudit();
        BeanUtils.copyProperties(parameter, wechatBetaMppAuthAudit);
        wechatBetaMppAuthAudit.setAuditId(IdWorkerProvider.next());

        wechatBetaMppAuthAudit.setTenantId(MultientyContext.getTenant().getTenantId());
        saveTE(wechatBetaMppAuthAudit);
        parameter.setAuditId(wechatBetaMppAuthAudit.getAuditId());
    }


    @Override
    public void enable(WechatBetaMppAuthAuditEnableParameter parameter) {
        WechatBetaMppAuthAudit wechatBetaMppAuthAudit = new WechatBetaMppAuthAudit();
        BeanUtils.copyProperties(parameter, wechatBetaMppAuthAudit);
        wechatBetaMppAuthAudit.setStatus(StatusEnum.NORMAL.getCode());
        update(wechatBetaMppAuthAudit, new LambdaQueryWrapper<WechatBetaMppAuthAudit>()
                .eq(WechatBetaMppAuthAudit::getTenantId, parameter.getTenantId())
                .eq(WechatBetaMppAuthAudit::getAuditId, parameter.getAuditId()));

    }

    @Override
    public void disable(WechatBetaMppAuthAuditDisableParameter parameter) {
        WechatBetaMppAuthAudit wechatBetaMppAuthAudit = new WechatBetaMppAuthAudit();
        BeanUtils.copyProperties(parameter, wechatBetaMppAuthAudit);
        wechatBetaMppAuthAudit.setStatus(StatusEnum.DISABLED.getCode());
        update(wechatBetaMppAuthAudit, new LambdaQueryWrapper<WechatBetaMppAuthAudit>()
                .eq(WechatBetaMppAuthAudit::getTenantId, parameter.getTenantId())
                .eq(WechatBetaMppAuthAudit::getAuditId, parameter.getAuditId()));
    }
}
