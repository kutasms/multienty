package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.sdk.request.express.MsgPluginApplyRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.express.QueryPluginApplyRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.express.ReturnPluginApplyRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.express.MsgPluginApplyResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.express.QueryPluginApplyResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.express.ReturnPluginApplyResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPExpressService;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WxTPExpressServiceImpl implements WxTPExpressService {
    private final WxTPApiExecutor executor;

    private final WechatAppService wechatAppService;

    @Override
    public MsgPluginApplyResponse applyMsgPlugin(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        MsgPluginApplyRequest request = new MsgPluginApplyRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public QueryPluginApplyResponse applyQueryPlugin(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        QueryPluginApplyRequest request = new QueryPluginApplyRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public ReturnPluginApplyResponse applyReturnPlugin(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        ReturnPluginApplyRequest request = new ReturnPluginApplyRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }
}
