package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.wechat.thirdparty.parameter.domain.ServerDomainModifyDirectlyParameter;
import com.chia.multienty.wechat.thirdparty.parameter.domain.ServerDomainModifyParameter;
import com.chia.multienty.wechat.thirdparty.parameter.domain.ThirdPartyJumpDomainModifyParameter;
import com.chia.multienty.wechat.thirdparty.parameter.domain.ThirdPartyServerDomainModifyParameter;
import com.chia.multienty.wechat.thirdparty.sdk.request.domain.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizerAccessTokenGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import com.chia.multienty.wechat.thirdparty.service.WxTPDomainService;
import com.chia.multienty.wechat.thirdparty.service.WxTPPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WxTPDomainServiceImpl implements WxTPDomainService {
    private final WxTPApiExecutor executor;
    private final WechatAppService wechatAppService;
    private final WxTPPlatformService wxTPPlatformService;
    @Override
    public EffectiveJumpDomainGetResponse getEffectiveJumpDomain() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        EffectiveJumpDomainGetRequest request = new EffectiveJumpDomainGetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public EffectiveServerDomainGetResponse getEffectiveServerDomain() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        EffectiveServerDomainGetRequest request = new EffectiveServerDomainGetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public JumpDomainConfirmFileGetResponse getJumpDomainConfirmFile() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        JumpDomainConfirmFileGetRequest request = new JumpDomainConfirmFileGetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public JumpDomainModifyDirectlyResponse modifyJumpDomainDirectly(String action, List<String> webViewDomains) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        JumpDomainModifyDirectlyRequest request = new JumpDomainModifyDirectlyRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        request.setAction(action);
        request.setWebViewDomain(webViewDomains);
        return executor.execute(request);
    }

    @Override
    public JumpDomainModifyResponse modifyJumpDomain(String action, List<String> webViewDomains) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        JumpDomainModifyRequest request = new JumpDomainModifyRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        request.setAction(action);
        request.setWebViewDomain(webViewDomains);
        return executor.execute(request);
    }

    @Override
    public ServerDomainModifyDirectlyResponse modifyServerDomainDirectly(ServerDomainModifyDirectlyParameter parameter) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        ServerDomainModifyDirectlyRequest request = new ServerDomainModifyDirectlyRequest();
        request.setAction(parameter.getAction());
        request.setRequestDomain(parameter.getRequestDomains());
        request.setWsRequestDomain(parameter.getWsRequestDomains());
        request.setUdpDomain(parameter.getUdpDomains());
        request.setTcpDomain(parameter.getTcpDomains());
        request.setDownloadDomain(parameter.getDownloadDomains());
        request.setUploadDomain(parameter.getUploadDomains());
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public ServerDomainModifyResponse modifyServerDomain(ServerDomainModifyParameter parameter) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        ServerDomainModifyRequest request = new ServerDomainModifyRequest();
        request.setAction(parameter.getAction());
        request.setRequestDomain(parameter.getRequestDomains());
        request.setWsRequestDomain(parameter.getWsRequestDomains());
        request.setUdpDomain(parameter.getUdpDomains());
        request.setTcpDomain(parameter.getTcpDomains());
        request.setDownloadDomain(parameter.getDownloadDomains());
        request.setUploadDomain(parameter.getUploadDomains());
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
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
