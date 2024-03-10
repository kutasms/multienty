package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.dto.WechatMppRegisterAuditDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.WechatMppRegisterAuditMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.WechatMppRegisterAudit;
import com.chia.multienty.core.service.WechatMppRegisterAuditService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppRegisterAuditDisableParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 微信小程序代码审核单 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-27
 */
@Service
public class WechatMppRegisterAuditServiceImpl extends KutaBaseServiceImpl<WechatMppRegisterAuditMapper, WechatMppRegisterAudit> implements WechatMppRegisterAuditService {


    @Override
    public WechatMppRegisterAuditDTO getDetail(WechatMppRegisterAuditDetailGetParameter parameter) {
        return selectJoinOne(WechatMppRegisterAuditDTO.class,
                        MPJWrappers.<WechatMppRegisterAudit>lambdaJoin().eq(WechatMppRegisterAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public void delete(WechatMppRegisterAuditDeleteParameter parameter) {
        removeByIdTE(parameter.getAuditId());
    }

    @Override
    public IPage<WechatMppRegisterAuditDTO> getPage(WechatMppRegisterAuditPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatMppRegisterAuditDTO.class,
                new MTLambdaWrapper<WechatMppRegisterAudit>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getAuditIds()), WechatMppRegisterAudit::getAuditId, parameter.getAuditIds())
        );
    }

    @Override
    public void save(WechatMppRegisterAuditSaveParameter parameter) {
        WechatMppRegisterAudit wechatMppRegisterAudit = new WechatMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatMppRegisterAudit);
        saveTE(wechatMppRegisterAudit);
        parameter.setAuditId(wechatMppRegisterAudit.getAuditId());
    }

    @Override
    public void update(WechatMppRegisterAuditUpdateParameter parameter) {
        WechatMppRegisterAudit wechatMppRegisterAudit = new WechatMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatMppRegisterAudit);
        updateByIdTE(wechatMppRegisterAudit);
    }
    @Override
    public void enable(WechatMppRegisterAuditEnableParameter parameter) {
        WechatMppRegisterAudit wechatMppRegisterAudit = new WechatMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatMppRegisterAudit);
        wechatMppRegisterAudit.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(wechatMppRegisterAudit);
    }

    @Override
    public void disable(WechatMppRegisterAuditDisableParameter parameter) {
        WechatMppRegisterAudit wechatMppRegisterAudit = new WechatMppRegisterAudit();
        BeanUtils.copyProperties(parameter, wechatMppRegisterAudit);
        wechatMppRegisterAudit.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(wechatMppRegisterAudit);
    }
}
