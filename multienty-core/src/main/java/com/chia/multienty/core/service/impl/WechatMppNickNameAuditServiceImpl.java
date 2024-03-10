package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chia.multienty.core.domain.dto.WechatMppNickNameAuditDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.WechatMppNickNameAuditMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.WechatMppNickNameAudit;
import com.chia.multienty.core.service.WechatMppNickNameAuditService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatMppNickNameAuditDisableParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 微信小程序昵称审核单 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-26
 */
@Service
public class WechatMppNickNameAuditServiceImpl extends KutaBaseServiceImpl<WechatMppNickNameAuditMapper, WechatMppNickNameAudit> implements WechatMppNickNameAuditService {


    @Override
    public WechatMppNickNameAuditDTO getDetail(WechatMppNickNameAuditDetailGetParameter parameter) {
        return selectJoinOne(WechatMppNickNameAuditDTO.class,
                        MPJWrappers.<WechatMppNickNameAudit>lambdaJoin().eq(WechatMppNickNameAudit::getAuditId, parameter.getAuditId()));
    }

    @Override
    public void delete(WechatMppNickNameAuditDeleteParameter parameter) {
        removeByIdTE(parameter.getAuditId());
    }

    @Override
    public IPage<WechatMppNickNameAuditDTO> getPage(WechatMppNickNameAuditPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatMppNickNameAuditDTO.class,
                new MTLambdaWrapper<WechatMppNickNameAudit>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getAuditIds()), WechatMppNickNameAudit::getAuditId, parameter.getAuditIds())
        );
    }

    @Override
    public WechatMppNickNameAudit getByNickName(String nickName) {
        return getOne(new LambdaQueryWrapper<WechatMppNickNameAudit>()
                .eq(WechatMppNickNameAudit::getNickName, nickName)
                .eq(WechatMppNickNameAudit::getStatus, StatusEnum.WAITING.getCode()));
    }

    @Override
    public void save(WechatMppNickNameAuditSaveParameter parameter) {
        WechatMppNickNameAudit wechatMppNickNameAudit = new WechatMppNickNameAudit();
        BeanUtils.copyProperties(parameter, wechatMppNickNameAudit);
        saveTE(wechatMppNickNameAudit);
        parameter.setAuditId(wechatMppNickNameAudit.getAuditId());
    }

    @Override
    public void update(WechatMppNickNameAuditUpdateParameter parameter) {
        WechatMppNickNameAudit wechatMppNickNameAudit = new WechatMppNickNameAudit();
        BeanUtils.copyProperties(parameter, wechatMppNickNameAudit);
        updateByIdTE(wechatMppNickNameAudit);
    }
    @Override
    public void enable(WechatMppNickNameAuditEnableParameter parameter) {
        WechatMppNickNameAudit wechatMppNickNameAudit = new WechatMppNickNameAudit();
        BeanUtils.copyProperties(parameter, wechatMppNickNameAudit);
        wechatMppNickNameAudit.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(wechatMppNickNameAudit);
    }

    @Override
    public void disable(WechatMppNickNameAuditDisableParameter parameter) {
        WechatMppNickNameAudit wechatMppNickNameAudit = new WechatMppNickNameAudit();
        BeanUtils.copyProperties(parameter, wechatMppNickNameAudit);
        wechatMppNickNameAudit.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(wechatMppNickNameAudit);
    }
}
