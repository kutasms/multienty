package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.wechat.thirdparty.sdk.request.member.TesterBindRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.member.TesterGetRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.member.TesterUnbindRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterBindResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterUnbindResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPMemberService;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WxTPMemberServiceImpl implements WxTPMemberService {
    private final WxTPApiExecutor executor;
    private final WechatAppService wechatAppService;

    @Override
    public TesterBindResponse bindTester(String appId, String wechatId) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        TesterBindRequest request = new TesterBindRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setWechatId(wechatId);
        return executor.execute(request);
    }

    @Override
    public TesterGetResponse getTester(String appId, String action) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        TesterGetRequest request = new TesterGetRequest();
        request.setAction(action);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public TesterUnbindResponse unbindTester(String appId, String wechatId, String userStr) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        TesterUnbindRequest request = new TesterUnbindRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setWechatId(wechatId);
        request.setUserStr(userStr);
        return executor.execute(request);
    }
}
