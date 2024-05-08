package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chia.multienty.core.domain.dto.WechatMppCodeAuditDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.WechatMppCodeAuditMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.wechat.*;
import com.chia.multienty.core.pojo.WechatMppCodeAudit;
import com.chia.multienty.core.service.WechatMppCodeAuditService;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 微信小程序代码审核单 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-27
 */
@Service
public class WechatMppCodeAuditServiceImpl extends KutaBaseServiceImpl<WechatMppCodeAuditMapper, WechatMppCodeAudit>
        implements WechatMppCodeAuditService {


    @Override
    public WechatMppCodeAuditDTO getDetail(WechatMppCodeAuditDetailGetParameter parameter) {
        return selectJoinOne(WechatMppCodeAuditDTO.class,
                        MPJWrappers.<WechatMppCodeAudit>lambdaJoin().eq(WechatMppCodeAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public WechatMppCodeAudit getByAppId(String appId) {
        Page<WechatMppCodeAudit> page = page(new Page<WechatMppCodeAudit>(1, 1),
                mtLambdaWrapper()
                        .eq(WechatMppCodeAudit::getTenantId, MultientyContext.getTenant().getTenantId())
                        .eq(WechatMppCodeAudit::getAppId, appId)
                        .orderByDesc(WechatMppCodeAudit::getCreateTime)
        );
        if(page.getSize() == 1) {
            return page.getRecords().get(0);
        }
        return null;
    }

    @Override
    public void delete(WechatMppCodeAuditDeleteParameter parameter) {
        removeByIdAndSharding(
                new WechatMppCodeAudit().setAuditId(parameter.getAuditId()).setTenantId(parameter.getTenantId()));
    }

    @Override
    public IPage<WechatMppCodeAuditDTO> getPage(WechatMppCodeAuditPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatMppCodeAuditDTO.class,
                new MTLambdaWrapper<WechatMppCodeAudit>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getAuditIds()), WechatMppCodeAudit::getAuditId, parameter.getAuditIds())
        );
    }

    @Override
    public void save(WechatMppCodeAuditSaveParameter parameter) {
        WechatMppCodeAudit wechatMppCodeAudit = new WechatMppCodeAudit();
        BeanUtils.copyProperties(parameter, wechatMppCodeAudit);
        saveTE(wechatMppCodeAudit);
        parameter.setAuditId(wechatMppCodeAudit.getAuditId());
    }

    @Override
    public void update(WechatMppCodeAuditUpdateParameter parameter) {
        WechatMppCodeAudit wechatMppCodeAudit = new WechatMppCodeAudit();
        BeanUtils.copyProperties(parameter, wechatMppCodeAudit);
        update(wechatMppCodeAudit, mtLambdaWrapper()
                .eq(WechatMppCodeAudit::getAuditId, parameter.getAuditId())
                .eq(WechatMppCodeAudit::getTenantId, parameter.getTenantId())
        );
    }
    @Override
    public void enable(WechatMppCodeAuditEnableParameter parameter) {
        WechatMppCodeAudit wechatMppCodeAudit = new WechatMppCodeAudit();
        BeanUtils.copyProperties(parameter, wechatMppCodeAudit);
        wechatMppCodeAudit.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(wechatMppCodeAudit);
    }

    @Override
    public void disable(WechatMppCodeAuditDisableParameter parameter) {
        WechatMppCodeAudit wechatMppCodeAudit = new WechatMppCodeAudit();
        BeanUtils.copyProperties(parameter, wechatMppCodeAudit);
        wechatMppCodeAudit.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(wechatMppCodeAudit);
    }
}
