package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chia.multienty.core.domain.dto.WechatMppTemplateDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.WechatMppTemplateMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.WechatMppTemplate;
import com.chia.multienty.core.service.WechatMppTemplateService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplatePageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppTemplateDisableParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 微信小程序模版 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@Service
public class WechatMppTemplateServiceImpl extends KutaBaseServiceImpl<WechatMppTemplateMapper, WechatMppTemplate> implements WechatMppTemplateService {


    @Override
    public WechatMppTemplateDTO getDetail(WechatMppTemplateDetailGetParameter parameter) {
        return selectJoinOne(WechatMppTemplateDTO.class,
                        MPJWrappers.<WechatMppTemplate>lambdaJoin().eq(WechatMppTemplate::getMppTemplateId, parameter.getMppTemplateId()));
    }

    @Override
    public List<WechatMppTemplate> getList(Long programId) {
        return list(new LambdaQueryWrapper<WechatMppTemplate>()
                .eq(WechatMppTemplate::getProgramId, programId));
    }

    @Override
    public void delete(WechatMppTemplateDeleteParameter parameter) {
        removeByIdTE(parameter.getMppTemplateId());
    }

    @Override
    public void batchDelete(Long programId) {
        List<WechatMppTemplate> list = list(new LambdaQueryWrapper<WechatMppTemplate>().select(WechatMppTemplate::getMppTemplateId).eq(WechatMppTemplate::getProgramId, programId));
        if(!ListUtil.isEmpty(list)) {
            List<Long> ids = list.stream().map(m -> m.getMppTemplateId()).collect(Collectors.toList());
            batchRemoveByIdTE(ids);
        }
    }

    @Override
    public IPage<WechatMppTemplateDTO> getPage(WechatMppTemplatePageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatMppTemplateDTO.class,
                new MTLambdaWrapper<WechatMppTemplate>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getMppTemplateIds()), WechatMppTemplate::getMppTemplateId, parameter.getMppTemplateIds())
        );
    }

    @Override
    public void save(WechatMppTemplateSaveParameter parameter) {
        WechatMppTemplate wechatMppTemplate = new WechatMppTemplate();
        BeanUtils.copyProperties(parameter, wechatMppTemplate);
        saveTE(wechatMppTemplate);
        parameter.setMppTemplateId(wechatMppTemplate.getMppTemplateId());
    }

    @Override
    public void update(WechatMppTemplateUpdateParameter parameter) {
        WechatMppTemplate wechatMppTemplate = new WechatMppTemplate();
        BeanUtils.copyProperties(parameter, wechatMppTemplate);
        updateByIdTE(wechatMppTemplate);
    }
    @Override
    public void enable(WechatMppTemplateEnableParameter parameter) {
        WechatMppTemplate wechatMppTemplate = new WechatMppTemplate();
        BeanUtils.copyProperties(parameter, wechatMppTemplate);
        wechatMppTemplate.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(wechatMppTemplate);
    }

    @Override
    public void disable(WechatMppTemplateDisableParameter parameter) {
        WechatMppTemplate wechatMppTemplate = new WechatMppTemplate();
        BeanUtils.copyProperties(parameter, wechatMppTemplate);
        wechatMppTemplate.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(wechatMppTemplate);
    }
}
