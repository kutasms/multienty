package com.chia.multienty.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.mapper.WechatAppMapper;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.pojo.WechatMppTemplate;
import com.chia.multienty.core.pojo.WechatPay;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.service.WechatMppTemplateService;
import com.chia.multienty.core.service.WechatPayService;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chia.multienty.core.parameter.wechat.WechatAppDetailGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppPageGetParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppDeleteParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppSaveParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppUpdateParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppEnableParameter;
import com.chia.multienty.core.parameter.wechat.WechatAppDisableParameter;
import com.github.yulichang.toolkit.MPJWrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 微信应用 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-21
 */
@Service
@RequiredArgsConstructor
public class WechatAppServiceImpl extends KutaBaseServiceImpl<WechatAppMapper, WechatApp> implements WechatAppService {


    private final WechatMppTemplateService wechatMppTemplateService;
    private final WechatPayService wechatPayService;

    private final YamlMultientyProperties yamlMultientyProperties;

    @Override
    public WechatAppDTO getDetail(WechatAppDetailGetParameter parameter) {
        WechatAppDTO app = selectJoinOne(WechatAppDTO.class,
                MPJWrappers.<WechatApp>lambdaJoin().eq(WechatApp::getProgramId, parameter.getProgramId()));
        if(parameter.getContainsTemplates()) {
            List<WechatMppTemplate> templates = wechatMppTemplateService.getList(parameter.getProgramId());
            app.setTemplates(templates);
        }
        if(parameter.getContainsPay()) {
            WechatPay pay = wechatPayService.getByTenantId(app.getTenantId());
            app.setPay(pay);
        }
        
        return app;
    }

    @Override
    public WechatApp getBy(Long tenantId,String appId) {
        if(appId == null) {
            throw new KutaRuntimeException(HttpResultEnum.ARG_LOSE_PATTERN);
        }
        return getOne(new LambdaQueryWrapper<WechatApp>().eq(WechatApp::getTenantId, tenantId).eq(WechatApp::getMiniAppId, appId));
    }

    @Override
    public WechatApp getByAppId(String appId) {
        return getOne(new LambdaQueryWrapper<WechatApp>().eq(WechatApp::getMiniAppId, appId));
    }

    @Override
    public WechatApp getByPreAuthCode(String preAuthCode) {
        return getOne(new LambdaQueryWrapper<WechatApp>().eq(WechatApp::getPreAuthCode, preAuthCode));
    }

    @Override
    public WechatApp getWaiting(Long tenantId) {
        return getOne(new LambdaQueryWrapper<WechatApp>()
                .eq(WechatApp::getStatus, StatusEnum.CREATED.getCode())
                .eq(WechatApp::getTenantId, tenantId));
    }

    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = {
            Exception.class})
    public void delete(WechatAppDeleteParameter parameter) {
        removeByIdTE(parameter.getProgramId());
        wechatMppTemplateService.batchDelete(parameter.getProgramId());
    }

    @Override
    public IPage<WechatAppDTO> getPage(WechatAppPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), WechatAppDTO.class,
                new MTLambdaWrapper<WechatApp>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getProgramIds()), WechatApp::getProgramId, parameter.getProgramIds())
        );
    }

    @Override
    public void save(WechatAppSaveParameter parameter) {
        WechatApp wechatApp = new WechatApp();
        BeanUtils.copyProperties(parameter, wechatApp);
        saveTE(wechatApp);
        parameter.setProgramId(wechatApp.getProgramId());
    }

    @Override
    public void update(WechatAppUpdateParameter parameter) {
        WechatApp wechatApp = new WechatApp();
        BeanUtils.copyProperties(parameter, wechatApp);
        updateByIdTE(wechatApp);
    }
    @Override
    public void enable(WechatAppEnableParameter parameter) {
        WechatApp wechatApp = new WechatApp();
        BeanUtils.copyProperties(parameter, wechatApp);
        wechatApp.setStatus(StatusEnum.NORMAL.getCode());
        updateByIdTE(wechatApp);
    }

    @Override
    public void disable(WechatAppDisableParameter parameter) {
        WechatApp wechatApp = new WechatApp();
        BeanUtils.copyProperties(parameter, wechatApp);
        wechatApp.setStatus(StatusEnum.DISABLED.getCode());
        updateByIdTE(wechatApp);
    }
}
