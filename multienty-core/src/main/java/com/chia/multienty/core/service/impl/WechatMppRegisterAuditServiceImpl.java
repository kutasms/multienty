package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.WechatMppRegisterAudit;
import com.chia.multienty.core.mapper.WechatMppRegisterAuditMapper;
import com.chia.multienty.core.service.WechatMppRegisterAuditService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.WechatMppRegisterAuditDTO;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDisableParameter;
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
 * 微信小程序注册审核单 服务实现类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-16
 */
@Service
@RequiredArgsConstructor
@DS(MultientyConstants.DS_SHARDING)
public class WechatMppRegisterAuditServiceImpl extends KutaBaseServiceImpl<WechatMppRegisterAuditMapper, WechatMppRegisterAudit> implements WechatMppRegisterAuditService {


    @Override
    public WechatMppRegisterAuditDTO getDetail(WechatMppRegisterAuditDetailGetParameter parameter) {
        return selectJoinOne(WechatMppRegisterAuditDTO.class,
                        MPJWrappers.<WechatMppRegisterAudit>lambdaJoin()
                        .eq(WechatMppRegisterAudit::getTenantId, parameter.getTenantId())
                        .eq(WechatMppRegisterAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public WechatMppRegisterAuditDTO getBy(String name, String code, String legalPersonaWechat, String legalPersonaName) {
        return selectJoinOne(WechatMppRegisterAuditDTO.class,mtLambdaWrapper()
                .eq(WechatMppRegisterAuditDTO::getName, name)
                .eq(WechatMppRegisterAuditDTO::getCode, code)
                .eq(WechatMppRegisterAuditDTO::getLegalPersonaWechat, legalPersonaWechat)
                .eq(WechatMppRegisterAuditDTO::getLegalPersonaName, legalPersonaName)
        );
    }



    @Override
    public void update(WechatMppRegisterAuditUpdateParameter parameter) {
        WechatMppRegisterAudit wechatMppRegisterAudit = new WechatMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatMppRegisterAudit);
        update(wechatMppRegisterAudit, new LambdaQueryWrapper<WechatMppRegisterAudit>()
                .eq(WechatMppRegisterAudit::getTenantId, parameter.getTenantId())
                .eq(WechatMppRegisterAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public void delete(WechatMppRegisterAuditDeleteParameter parameter) {
        removeTE(new LambdaQueryWrapper<WechatMppRegisterAudit>()
                .eq(WechatMppRegisterAudit::getTenantId, parameter.getTenantId())
                .eq(WechatMppRegisterAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public IPage<WechatMppRegisterAuditDTO> getPage(WechatMppRegisterAuditPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatMppRegisterAuditDTO.class,
                new MTLambdaWrapper<WechatMppRegisterAudit>()
                        .solveGenericParameters(parameter)
                        .eq(WechatMppRegisterAudit::getTenantId, parameter.getTenantId())
                        .in(!ListUtil.isEmpty(parameter.getAuditIds()),
                                WechatMppRegisterAudit::getAuditId,
                                parameter.getAuditIds())
        );
    }

    @Override
    public void save(WechatMppRegisterAuditSaveParameter parameter) {
        WechatMppRegisterAudit wechatMppRegisterAudit = new WechatMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatMppRegisterAudit);
        wechatMppRegisterAudit.setAuditId(IdWorkerProvider.next());

        wechatMppRegisterAudit.setTenantId(MultientyContext.getTenant().getTenantId());
        saveTE(wechatMppRegisterAudit);
        parameter.setAuditId(wechatMppRegisterAudit.getAuditId());
    }


    @Override
    public void enable(WechatMppRegisterAuditEnableParameter parameter) {
        WechatMppRegisterAudit wechatMppRegisterAudit = new WechatMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatMppRegisterAudit);
        wechatMppRegisterAudit.setStatus(StatusEnum.NORMAL.getCode());
        update(wechatMppRegisterAudit, new LambdaQueryWrapper<WechatMppRegisterAudit>()
                .eq(WechatMppRegisterAudit::getTenantId, parameter.getTenantId())
                .eq(WechatMppRegisterAudit::getAuditId, parameter.getAuditId()));

    }

    @Override
    public void disable(WechatMppRegisterAuditDisableParameter parameter) {
        WechatMppRegisterAudit wechatMppRegisterAudit = new WechatMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatMppRegisterAudit);
        wechatMppRegisterAudit.setStatus(StatusEnum.DISABLED.getCode());
        update(wechatMppRegisterAudit, new LambdaQueryWrapper<WechatMppRegisterAudit>()
                .eq(WechatMppRegisterAudit::getTenantId, parameter.getTenantId())
                .eq(WechatMppRegisterAudit::getAuditId, parameter.getAuditId()));
    }
}
