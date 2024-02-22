package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chia.multienty.core.domain.dto.WechatPayDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.WechatPayMapper;
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.WechatPay;
import com.chia.multienty.core.service.WechatPayService;
import com.chia.multienty.core.util.ListUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatPayDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatPaySaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatPayDisableParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * 微信支付 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@Service
public class WechatPayServiceImpl extends KutaBaseServiceImpl<WechatPayMapper, WechatPay> implements WechatPayService {


    @Override
    public WechatPayDTO getDetail(WechatPayDetailGetParameter parameter) {
        return selectJoinOne(WechatPayDTO.class,
                        MPJWrappers.<WechatPay>lambdaJoin().eq(WechatPay::getWxPayId, parameter.getWxPayId()));
    }

    @Override
    public void delete(WechatPayDeleteParameter parameter) {
        removeByIdTE(parameter.getWxPayId());
    }

    @Override
    public IPage<WechatPayDTO> getPage(WechatPayPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatPayDTO.class,
                new KutaLambdaWrapper<WechatPay>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getWxPayIds()), WechatPay::getWxPayId, parameter.getWxPayIds())
        );
    }

    @Override
    public void save(WechatPaySaveParameter parameter) {
        WechatPay wechatPay = new WechatPay();
        BeanUtils.copyProperties(parameter, wechatPay);
        saveTE(wechatPay);
        parameter.setWxPayId(wechatPay.getWxPayId());
    }

    @Override
    public void update(WechatPayUpdateParameter parameter) {
        WechatPay wechatPay = new WechatPay();
        BeanUtils.copyProperties(parameter, wechatPay);
        updateByIdTE(wechatPay);
    }
    @Override
    public void enable(WechatPayEnableParameter parameter) {
        WechatPay wechatPay = new WechatPay();
        BeanUtils.copyProperties(parameter, wechatPay);
        wechatPay.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(wechatPay);
    }

    @Override
    public void disable(WechatPayDisableParameter parameter) {
        WechatPay wechatPay = new WechatPay();
        BeanUtils.copyProperties(parameter, wechatPay);
        wechatPay.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(wechatPay);
    }

    @Override
    public WechatPay getByTenantId(Long tenantId) {
        return getOne(new LambdaQueryWrapper<WechatPay>().eq(WechatPay::getTenantId, tenantId));
    }
}
