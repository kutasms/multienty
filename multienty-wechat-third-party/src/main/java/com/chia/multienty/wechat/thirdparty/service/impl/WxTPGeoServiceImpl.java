package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.wechat.thirdparty.sdk.request.geo.PrivacyInterfaceApplyRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.geo.PrivacyInterfaceGetRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.geo.PrivacyInterfaceApplyResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.geo.PrivacyInterfaceGetResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPGeoService;
import com.chia.multienty.wechat.thirdparty.parameter.geo.PrivacyInterfaceApplyParameter;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WxTPGeoServiceImpl implements WxTPGeoService {
    private final WxTPApiExecutor executor;
    private final WechatAppService wechatAppService;

    @Override
    public PrivacyInterfaceApplyResponse applyPrivacyInterface(PrivacyInterfaceApplyParameter parameter) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), MultientyContext.getMppAppId());
        PrivacyInterfaceApplyRequest request = new PrivacyInterfaceApplyRequest();
        BeanUtils.copyProperties(parameter, request);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public PrivacyInterfaceGetResponse getPrivacyInterface(String appId) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        PrivacyInterfaceGetRequest request = new PrivacyInterfaceGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }
}
