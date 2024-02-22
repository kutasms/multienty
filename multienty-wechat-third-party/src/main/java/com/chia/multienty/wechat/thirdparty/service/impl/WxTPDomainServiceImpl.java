package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.sdk.request.domain.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.*;
import com.chia.multienty.wechat.thirdparty.service.WxTPDomainService;
import com.chia.multienty.wechat.thirdparty.parameter.domain.ServerDomainModifyDirectlyParameter;
import com.chia.multienty.wechat.thirdparty.parameter.domain.ServerDomainModifyParameter;
import com.chia.multienty.wechat.thirdparty.parameter.domain.ThirdPartyJumpDomainModifyParameter;
import com.chia.multienty.wechat.thirdparty.parameter.domain.ThirdPartyServerDomainModifyParameter;
import com.chia.multienty.wechat.thirdparty.sdk.request.domain.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.*;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WxTPDomainServiceImpl implements WxTPDomainService {
    private final WxTPApiExecutor executor;
    private final WechatAppService wechatAppService;

    @Override
    public EffectiveJumpDomainGetResponse getEffectiveJumpDomain(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        EffectiveJumpDomainGetRequest request = new EffectiveJumpDomainGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public EffectiveServerDomainGetResponse getEffectiveServerDomain(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        EffectiveServerDomainGetRequest request = new EffectiveServerDomainGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public JumpDomainConfirmFileGetResponse getJumpDomainConfirmFile(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        JumpDomainConfirmFileGetRequest request = new JumpDomainConfirmFileGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public JumpDomainModifyDirectlyResponse modifyJumpDomainDirectly(String appId, String action, List<String> webViewDomains) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        JumpDomainModifyDirectlyRequest request = new JumpDomainModifyDirectlyRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setAction(action);
        request.setWebViewDomain(webViewDomains);
        return executor.execute(request);
    }

    @Override
    public JumpDomainModifyResponse modifyJumpDomain(String appId, String action, List<String> webViewDomains) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        JumpDomainModifyRequest request = new JumpDomainModifyRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setAction(action);
        request.setWebViewDomain(webViewDomains);
        return executor.execute(request);
    }

    @Override
    public ServerDomainModifyDirectlyResponse modifyServerDomainDirectly(ServerDomainModifyDirectlyParameter parameter) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), MultiTenantContext.getMppAppId());
        ServerDomainModifyDirectlyRequest request = new ServerDomainModifyDirectlyRequest();
        request.setRequestDomain(parameter.getRequestDomains());
        request.setWsRequestDomain(parameter.getWsRequestDomains());
        request.setUdpDomain(parameter.getUdpDomains());
        request.setTcpDomain(parameter.getTcpDomains());
        request.setDownloadDomain(parameter.getDownloadDomains());
        request.setUploadDomain(parameter.getUploadDomains());
        return executor.execute(request);
    }

    @Override
    public ServerDomainModifyResponse modifyServerDomain(ServerDomainModifyParameter parameter) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), MultiTenantContext.getMppAppId());
        ServerDomainModifyRequest request = new ServerDomainModifyRequest();
        request.setRequestDomain(parameter.getRequestDomains());
        request.setWsRequestDomain(parameter.getWsRequestDomains());
        request.setUdpDomain(parameter.getUdpDomains());
        request.setTcpDomain(parameter.getTcpDomains());
        request.setDownloadDomain(parameter.getDownloadDomains());
        request.setUploadDomain(parameter.getUploadDomains());
        return executor.execute(request);
    }

    @Override
    public ThirdPartyJumpDomainConfirmFileGetResponse getThirdPartyJumpDomainConfirmFile() {
        ThirdPartyJumpDomainConfirmFileGetRequest request = new ThirdPartyJumpDomainConfirmFileGetRequest();
        return executor.execute(request);
    }

    @Override
    public ThirdPartyJumpDomainModifyResponse modifyThirdPartyJumpDomain(ThirdPartyJumpDomainModifyParameter parameter) {
        ThirdPartyJumpDomainModifyRequest request = new ThirdPartyJumpDomainModifyRequest();
        request.setAction(parameter.getAction());
        request.setWxaJumpH5Domain(parameter.getWxaJumpH5Domain());
        request.setIsModifyPublishedTogether(parameter.getIsModifyPublishedTogether());
        return executor.execute(request);
    }

    @Override
    public ThirdPartyServerDomainModifyResponse modifyThirdPartyServerDomain(ThirdPartyServerDomainModifyParameter parameter) {
        ThirdPartyServerDomainModifyRequest request = new ThirdPartyServerDomainModifyRequest();
        request.setAction(parameter.getAction());
        request.setWxaServerDomain(parameter.getWxaServerDomain());
        request.setIsModifyPublishedTogether(parameter.getIsModifyPublishedTogether());
        return executor.execute(request);
    }


}
