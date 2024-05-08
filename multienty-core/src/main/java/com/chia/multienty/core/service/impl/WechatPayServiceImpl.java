package com.chia.multienty.core.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.domain.dto.WechatPayDTO;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.dubbo.service.DubboMultientyService;
import com.chia.multienty.core.mapper.WechatPayMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.wechat.*;
import com.chia.multienty.core.pojo.WechatPay;
import com.chia.multienty.core.service.WechatPayService;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.core.util.ListUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * <p>
 * 微信支付 服务实现类
 * </p>
 *
 * @author Multienty Auto Generator
 * @since 2024-04-15
 */
@Service
@RequiredArgsConstructor
@DS(MultientyConstants.DS_SHARDING)
public class WechatPayServiceImpl extends KutaBaseServiceImpl<WechatPayMapper, WechatPay> implements WechatPayService {

    @Autowired(required = false)
    private DubboMultientyService dubboMultientyService;


    @Override
    public WechatPayDTO getDetail(WechatPayDetailGetParameter parameter) {
        return selectJoinOne(WechatPayDTO.class,
                        MPJWrappers.<WechatPay>lambdaJoin()
                        .eq(WechatPay::getTenantId, parameter.getTenantId())
                        .eq(WechatPay::getWxPayId, parameter.getWxPayId()));
    }

    @Override
    public void update(WechatPayUpdateParameter parameter) {
        WechatPay wechatPay = new WechatPay();
        BeanUtils.copyProperties(parameter, wechatPay);
        update(wechatPay, new LambdaQueryWrapper<WechatPay>()
                .eq(WechatPay::getTenantId, parameter.getTenantId())
                .eq(WechatPay::getWxPayId, parameter.getWxPayId()));
    }

    @Override
    public WechatPay getBy(Long tenantId, Long programId) {
        return getOne(mtLambdaWrapper()
                .eq(WechatPay::getTenantId, tenantId)
                .eq(WechatPay::getProgramId, programId)
        );
    }
    @Override
    public WechatPayDTO getDTOBy(Long tenantId) {
        return selectJoinOne(WechatPayDTO.class, mtLambdaWrapper().eq(WechatPay::getTenantId, tenantId));
    }
    @Override
    public void delete(WechatPayDeleteParameter parameter) {
        removeTE(new LambdaQueryWrapper<WechatPay>()
                .eq(WechatPay::getTenantId, parameter.getTenantId())
                .eq(WechatPay::getWxPayId, parameter.getWxPayId()));
    }

    @Override
    public IPage<WechatPayDTO> getPage(WechatPayPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatPayDTO.class,
                new MTLambdaWrapper<WechatPay>()
                        .solveGenericParameters(parameter)
                        .eq(WechatPay::getTenantId, parameter.getTenantId())
                        .eq(WechatPay::getProgramId, parameter.getProgramId())
                        .in(!ListUtil.isEmpty(parameter.getWxPayIds()),
                                WechatPay::getWxPayId,
                                parameter.getWxPayIds())
        );
    }

    @Override
    @GlobalTransactional
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void save(WechatPaySaveParameter parameter) {
        WechatPay wechatPay = new WechatPay();
        BeanUtils.copyProperties(parameter, wechatPay);
        wechatPay.setTenantId(MultientyContext.getTenant().getTenantId());
        saveTE(wechatPay);
        parameter.getCertificateFiles().get(0).setMetaId(wechatPay.getWxPayId());
        parameter.getPrivateKeyFiles().get(0).setMetaId(wechatPay.getWxPayId());
        dubboMultientyService.updateFilesMetaId(Arrays.asList(
                parameter.getCertificateFiles().get(0),
                parameter.getPrivateKeyFiles().get(0)
        ));

        parameter.setWxPayId(wechatPay.getWxPayId());
    }


    @Override
    public void enable(WechatPayEnableParameter parameter) {
        WechatPay wechatPay = new WechatPay();
        BeanUtils.copyProperties(parameter, wechatPay);
        wechatPay.setStatus(StatusEnum.NORMAL.getCode());
        update(wechatPay, new LambdaQueryWrapper<WechatPay>()
                .eq(WechatPay::getTenantId, parameter.getTenantId())
                .eq(WechatPay::getWxPayId, parameter.getWxPayId()));

    }

    @Override
    public void disable(WechatPayDisableParameter parameter) {
        WechatPay wechatPay = new WechatPay();
        BeanUtils.copyProperties(parameter, wechatPay);
        wechatPay.setStatus(StatusEnum.DISABLED.getCode());
        update(wechatPay, new LambdaQueryWrapper<WechatPay>()
                .eq(WechatPay::getTenantId, parameter.getTenantId())
                .eq(WechatPay::getWxPayId, parameter.getWxPayId()));
    }
}
