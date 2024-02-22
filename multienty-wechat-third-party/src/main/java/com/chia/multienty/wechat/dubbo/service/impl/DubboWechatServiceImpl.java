package com.chia.multienty.wechat.dubbo.service.impl;

import com.chia.multienty.core.domain.bo.WxLoginResponseBO;
import com.chia.multienty.core.dubbo.service.DubboWechatService;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.LoginResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPBaseInfoService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

@DubboService
@RequiredArgsConstructor
public class DubboWechatServiceImpl implements DubboWechatService {
    private final WxTPBaseInfoService wxTPBaseInfoService;
    @Override
    public WxLoginResponseBO login(String appId, String jsCode) {
        LoginResponse response = wxTPBaseInfoService.login(appId, jsCode);
        WxLoginResponseBO bo = new WxLoginResponseBO();
        BeanUtils.copyProperties(response, bo);
        return bo;
    }
}
