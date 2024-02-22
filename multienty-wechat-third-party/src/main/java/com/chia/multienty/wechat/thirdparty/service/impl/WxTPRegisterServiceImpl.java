package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.define.register.EnterpriseVerifyInfo;
import com.chia.multienty.wechat.thirdparty.sdk.request.register.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.*;
import com.chia.multienty.wechat.thirdparty.service.WxTPRegisterService;
import com.chia.multienty.wechat.thirdparty.parameter.register.EnterpriseMiniProgramRegisterParameter;
import com.chia.multienty.wechat.thirdparty.sdk.request.register.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.*;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WxTPRegisterServiceImpl implements WxTPRegisterService {
    private final WxTPApiExecutor executor;
    private final YamlMultiTenantProperties properties;
    private final WechatAppService wechatAppService;

    @Override
    public BetaMiniProgramNickNameModifyResponse modifyBetaMiniProgramNickName(String appId, String name) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        BetaMiniProgramNickNameModifyRequest request = new BetaMiniProgramNickNameModifyRequest();
        request.setName(name);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public BetaMiniProgramRegisterResponse registerBetaMiniProgram(String name, String openId) {
        BetaMiniProgramRegisterRequest request = new BetaMiniProgramRegisterRequest();
        request.setName(name);
        request.setOpenid(openId);
        return executor.execute(request);
    }

    @Override
    public BetaMiniProgramVerifyResponse verifyBetaMiniProgram(String appId, EnterpriseVerifyInfo verifyInfo) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        BetaMiniProgramVerifyRequest request = new BetaMiniProgramVerifyRequest();
        request.setVerifyInfo(verifyInfo);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public EnterpriseMiniProgramRegisterResponse registerEnterpriseMiniProgram(EnterpriseMiniProgramRegisterParameter parameter) {
        EnterpriseMiniProgramRegisterRequest request = new EnterpriseMiniProgramRegisterRequest();
        BeanUtils.copyProperties(parameter,request);
        return executor.execute(request);
    }

    @Override
    public MiniProgramRegisterByOfficialAccountResponse registerMiniProgramByOfficialAccount(String appId, String ticket) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        MiniProgramRegisterByOfficialAccountRequest request = new MiniProgramRegisterByOfficialAccountRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setTicket(ticket);
        return executor.execute(request);
    }
}
