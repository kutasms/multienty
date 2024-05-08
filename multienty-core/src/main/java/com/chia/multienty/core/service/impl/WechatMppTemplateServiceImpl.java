package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.wechat.WxMppTemplateType;
import com.chia.multienty.core.pojo.WechatMppTemplate;
import com.chia.multienty.core.mapper.WechatMppTemplateMapper;
import com.chia.multienty.core.service.WechatMppTemplateService;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.dto.WechatMppTemplateDTO;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplatePageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDisableParameter;
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

import java.util.List;

/**
 * <p>
 * 微信小程序模版 服务实现类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */
@Service
@RequiredArgsConstructor
@DS(MultientyConstants.DS_SHARDING)
public class WechatMppTemplateServiceImpl extends KutaBaseServiceImpl<WechatMppTemplateMapper, WechatMppTemplate> implements WechatMppTemplateService {


    @Override
    public WechatMppTemplateDTO getDetail(WechatMppTemplateDetailGetParameter parameter) {
        return selectJoinOne(WechatMppTemplateDTO.class,
                        MPJWrappers.<WechatMppTemplate>lambdaJoin()
                        .eq(WechatMppTemplate::getTenantId, parameter.getTenantId())
                        .eq(WechatMppTemplate::getMppTemplateId, parameter.getMppTemplateId()));
    }

    @Override
    public List<WechatMppTemplate> getList(Long programId) {
        return list(mtLambdaWrapper()
                .eq(WechatMppTemplate::getProgramId, programId)
                .eq(WechatMppTemplate::getTenantId, MultientyContext.getTenant().getTenantId()));
    }

    @Override
    public void update(WechatMppTemplateUpdateParameter parameter) {
        WechatMppTemplate wechatMppTemplate = new WechatMppTemplate();
        BeanUtils.copyProperties(parameter, wechatMppTemplate);
        update(wechatMppTemplate, new LambdaQueryWrapper<WechatMppTemplate>()
                .eq(WechatMppTemplate::getTenantId, parameter.getTenantId())
                .eq(WechatMppTemplate::getMppTemplateId, parameter.getMppTemplateId()));
    }

    @Override
    public void delete(WechatMppTemplateDeleteParameter parameter) {
        removeTE(new LambdaQueryWrapper<WechatMppTemplate>()
                .eq(WechatMppTemplate::getTenantId, parameter.getTenantId())
                .eq(WechatMppTemplate::getMppTemplateId, parameter.getMppTemplateId()));
    }

    @Override
    public void batchDelete(Long programId) {
        removeTE(mtLambdaWrapper()
                .eq(WechatMppTemplate::getProgramId, programId)
                .eq(WechatMppTemplate::getTenantId, MultientyContext.getTenant().getTenantId()));
    }

    @Override
    public IPage<WechatMppTemplateDTO> getPage(WechatMppTemplatePageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatMppTemplateDTO.class,
                new MTLambdaWrapper<WechatMppTemplate>()
                        .solveGenericParameters(parameter)
                        .eq(WechatMppTemplate::getTenantId, parameter.getTenantId())
                        .in(!ListUtil.isEmpty(parameter.getMppTemplateIds()),
                                WechatMppTemplate::getMppTemplateId,
                                parameter.getMppTemplateIds())
        );
    }

    @Override
    public void save(WechatMppTemplateSaveParameter parameter) {
        WechatMppTemplate wechatMppTemplate = new WechatMppTemplate();
        BeanUtils.copyProperties(parameter, wechatMppTemplate);
        wechatMppTemplate.setMppTemplateId(IdWorkerProvider.next());

        wechatMppTemplate.setTenantId(MultientyContext.getTenant().getTenantId());
        saveTE(wechatMppTemplate);
        parameter.setMppTemplateId(wechatMppTemplate.getMppTemplateId());
    }


    @Override
    public void enable(WechatMppTemplateEnableParameter parameter) {
        WechatMppTemplate wechatMppTemplate = new WechatMppTemplate();
        BeanUtils.copyProperties(parameter, wechatMppTemplate);
        wechatMppTemplate.setStatus(StatusEnum.NORMAL.getCode());
        update(wechatMppTemplate, new LambdaQueryWrapper<WechatMppTemplate>()
                .eq(WechatMppTemplate::getTenantId, parameter.getTenantId())
                .eq(WechatMppTemplate::getMppTemplateId, parameter.getMppTemplateId()));

    }

    @Override
    public void disable(WechatMppTemplateDisableParameter parameter) {
        WechatMppTemplate wechatMppTemplate = new WechatMppTemplate();
        BeanUtils.copyProperties(parameter, wechatMppTemplate);
        wechatMppTemplate.setStatus(StatusEnum.DISABLED.getCode());
        update(wechatMppTemplate, new LambdaQueryWrapper<WechatMppTemplate>()
                .eq(WechatMppTemplate::getTenantId, parameter.getTenantId())
                .eq(WechatMppTemplate::getMppTemplateId, parameter.getMppTemplateId()));
    }

    @Override
    public WechatMppTemplate getOne(Long tenantId, Long programId, WxMppTemplateType templateType) {
        return getOne(mtLambdaWrapper()
                .eq(WechatMppTemplate::getTenantId, tenantId)
                .eq(WechatMppTemplate::getProgramId, programId)
                .eq(WechatMppTemplate::getType, templateType.getValue().shortValue())
        );
    }
}
