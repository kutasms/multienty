package com.chia.multienty.wechat.dubbo.service.impl;

import com.chia.multienty.core.annotation.CachedMethod;
import com.chia.multienty.core.domain.bo.WxLoginResponseBO;
import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.domain.wechat.WxMppTemplateType;
import com.chia.multienty.core.dubbo.service.DubboWechatService;
import com.chia.multienty.core.parameter.wechat.WechatAppDetailGetParameter;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.strategy.pay.domain.PayType;
import com.chia.multienty.wechat.mpp.service.MppTemplateService;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.LoginResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPBaseInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

@DubboService
@RequiredArgsConstructor
public class DubboWechatServiceImpl implements DubboWechatService {
    private final WxTPBaseInfoService wxTPBaseInfoService;
    private final WechatAppService wechatAppService;
    private final MppTemplateService mppTemplateService;
    @Override
    public WechatAppDTO getWechatApp(WechatAppDetailGetParameter parameter) {
        return wechatAppService.getDetail(parameter);
    }

    @Override
    @CachedMethod(cacheKeyPrefix = "PAY_TYPE")
    public PayType getPayType(Long programId) {
        return wechatAppService.getPayType(programId);
    }

    @Override
    public WxLoginResponseBO login(String appId, String jsCode) {
        LoginResponse response = wxTPBaseInfoService.login(appId, jsCode);
        WxLoginResponseBO bo = new WxLoginResponseBO();
        BeanUtils.copyProperties(response, bo);
        return bo;
    }

    @Override
    public String sendScribeMessage(Long tenantId, Long programId, WxMppTemplateType templateType,String subOpenId, Object data) {
        return mppTemplateService.sendScribeMessage(programId, tenantId, templateType, data, subOpenId);
    }

}
