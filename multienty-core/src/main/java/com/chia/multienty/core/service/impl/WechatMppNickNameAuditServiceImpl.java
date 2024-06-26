package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.pojo.WechatMppNickNameAudit;
import com.chia.multienty.core.mapper.WechatMppNickNameAuditMapper;
import com.chia.multienty.core.service.WechatMppNickNameAuditService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.WechatMppNickNameAuditDTO;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDisableParameter;
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
 * 微信小程序昵称审核单 服务实现类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */
@Service
@RequiredArgsConstructor
@DS(MultientyConstants.DS_SHARDING)
public class WechatMppNickNameAuditServiceImpl extends KutaBaseServiceImpl<WechatMppNickNameAuditMapper, WechatMppNickNameAudit> implements WechatMppNickNameAuditService {


    @Override
    public WechatMppNickNameAuditDTO getDetail(WechatMppNickNameAuditDetailGetParameter parameter) {
        return selectJoinOne(WechatMppNickNameAuditDTO.class,
                        MPJWrappers.<WechatMppNickNameAudit>lambdaJoin()
                        .eq(WechatMppNickNameAudit::getTenantId, parameter.getTenantId())
                        .eq(WechatMppNickNameAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public WechatMppNickNameAuditDTO getDTOByOpenAuditId(String openAuditId) {
        return selectJoinOne(WechatMppNickNameAuditDTO.class, mtLambdaWrapper()
                .eq(WechatMppNickNameAudit::getOpenAuditId, openAuditId));
    }
    @Override
    public void update(WechatMppNickNameAuditUpdateParameter parameter) {
        WechatMppNickNameAudit wechatMppNickNameAudit = new WechatMppNickNameAudit();
        BeanUtils.copyProperties(parameter, wechatMppNickNameAudit);
        update(wechatMppNickNameAudit, new LambdaQueryWrapper<WechatMppNickNameAudit>()
                .eq(WechatMppNickNameAudit::getTenantId, parameter.getTenantId())
                .eq(WechatMppNickNameAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public void delete(WechatMppNickNameAuditDeleteParameter parameter) {
        removeTE(new LambdaQueryWrapper<WechatMppNickNameAudit>()
                .eq(WechatMppNickNameAudit::getTenantId, parameter.getTenantId())
                .eq(WechatMppNickNameAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public IPage<WechatMppNickNameAuditDTO> getPage(WechatMppNickNameAuditPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatMppNickNameAuditDTO.class,
                new MTLambdaWrapper<WechatMppNickNameAudit>()
                        .solveGenericParameters(parameter)
                        .eq(WechatMppNickNameAudit::getTenantId, parameter.getTenantId())
                        .in(!ListUtil.isEmpty(parameter.getAuditIds()),
                                WechatMppNickNameAudit::getAuditId,
                                parameter.getAuditIds())
        );
    }

    @Override
    public void save(WechatMppNickNameAuditSaveParameter parameter) {
        WechatMppNickNameAudit wechatMppNickNameAudit = new WechatMppNickNameAudit();
        BeanUtils.copyProperties(parameter, wechatMppNickNameAudit);
        wechatMppNickNameAudit.setAuditId(IdWorkerProvider.next());

        wechatMppNickNameAudit.setTenantId(MultientyContext.getTenant().getTenantId());
        saveTE(wechatMppNickNameAudit);
        parameter.setAuditId(wechatMppNickNameAudit.getAuditId());
    }


    @Override
    public void enable(WechatMppNickNameAuditEnableParameter parameter) {
        WechatMppNickNameAudit wechatMppNickNameAudit = new WechatMppNickNameAudit();
        BeanUtils.copyProperties(parameter, wechatMppNickNameAudit);
        wechatMppNickNameAudit.setStatus(StatusEnum.NORMAL.getCode());
        update(wechatMppNickNameAudit, new LambdaQueryWrapper<WechatMppNickNameAudit>()
                .eq(WechatMppNickNameAudit::getTenantId, parameter.getTenantId())
                .eq(WechatMppNickNameAudit::getAuditId, parameter.getAuditId()));

    }

    @Override
    public void disable(WechatMppNickNameAuditDisableParameter parameter) {
        WechatMppNickNameAudit wechatMppNickNameAudit = new WechatMppNickNameAudit();
        BeanUtils.copyProperties(parameter, wechatMppNickNameAudit);
        wechatMppNickNameAudit.setStatus(StatusEnum.DISABLED.getCode());
        update(wechatMppNickNameAudit, new LambdaQueryWrapper<WechatMppNickNameAudit>()
                .eq(WechatMppNickNameAudit::getTenantId, parameter.getTenantId())
                .eq(WechatMppNickNameAudit::getAuditId, parameter.getAuditId()));
    }
}
