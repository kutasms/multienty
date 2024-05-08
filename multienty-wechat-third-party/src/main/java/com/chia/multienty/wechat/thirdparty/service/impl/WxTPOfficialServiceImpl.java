package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.wechat.thirdparty.parameter.oa.JumpQRCodeAddParameter;
import com.chia.multienty.wechat.thirdparty.parameter.oa.JumpQRCodeGetParameter;
import com.chia.multienty.wechat.thirdparty.parameter.oa.MiniProgramLinkParameter;
import com.chia.multienty.wechat.thirdparty.sdk.request.oa.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.*;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import com.chia.multienty.wechat.thirdparty.service.WxTPOfficialService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class WxTPOfficialServiceImpl implements WxTPOfficialService {
    private final WxTPApiExecutor executor;
    private final YamlMultientyProperties properties;
    private final WechatAppService wechatAppService;
    private final static String PATTERN_AUTH_URL = "https://mp.weixin.qq.com/cgi-bin/fastregisterauth?appid=%s&component_appid=%s&copy_wx_verify=%s&redirect_uri=%s";
    @Override
    public JumpQRCodeAddResponse addJumpQRCode(JumpQRCodeAddParameter parameter) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), parameter.getAppId());
        JumpQRCodeAddRequest request = new JumpQRCodeAddRequest();
        BeanUtils.copyProperties(parameter, request);
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setAppId(parameter.getAppId());
        return executor.execute(request);
    }

    @Override
    public JumpQRCodeDeleteResponse deleteJumpQRCode(String appId, String prefix) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        JumpQRCodeDeleteRequest request = new JumpQRCodeDeleteRequest();
        request.setAppId(appId);
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setPrefix(prefix);
        return executor.execute(request);
    }

    @Override
    public JumpQRCodeGetResponse getJumpQRCode(JumpQRCodeGetParameter parameter) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), parameter.getAppId());
        JumpQRCodeGetRequest request = new JumpQRCodeGetRequest();
        BeanUtils.copyProperties(parameter, request);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public JumpQRCodePublishResponse publishJumpQRCode(String appId, String prefix) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        JumpQRCodePublishRequest request = new JumpQRCodePublishRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setPrefix(prefix);
        return executor.execute(request);
    }

    @Override
    public LinkForShowGetResponse getLinkForShow(String appId, Integer page, Integer num) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        LinkForShowGetRequest request = new LinkForShowGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setNum(num);
        request.setPage(page);
        return executor.execute(request);
    }

    @Override
    public LinkMiniProgramGetResponse getLinkMiniProgram(String appId) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        LinkMiniProgramGetRequest request = new LinkMiniProgramGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public MiniProgramLinkResponse linkMiniProgram(MiniProgramLinkParameter parameter) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), parameter.getAppId());
        MiniProgramLinkRequest request = new MiniProgramLinkRequest();
        BeanUtils.copyProperties(parameter, request);
        return executor.execute(request);
    }

    @Override
    public MiniProgramUnlinkResponse unlinkMiniProgram(String appId) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        MiniProgramUnlinkRequest request = new MiniProgramUnlinkRequest();
        request.setAppId(appId);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public ShowItemGetResponse getShowItem(String appId) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        ShowItemGetRequest request = new ShowItemGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public ShowItemSetResponse setShowItem(String appId, Integer wxaSubscribeBizFlag) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        ShowItemSetRequest request = new ShowItemSetRequest();
        request.setAppId(appId);
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setWxaSubscribeBizFlag(wxaSubscribeBizFlag);
        return executor.execute(request);
    }

    @Override
    public TempAssetAddResponse addTempAsset(String appId, MultipartFile file, String type) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        TempAssetAddRequest request = new TempAssetAddRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setFile(file);
        request.setType(type);
        return executor.execute(request);
    }

    @Override
    @SneakyThrows
    public OfficialAccountAuthLinkGenerateResponse generateOfficialAccountAuthLink(
            OfficialAccountAuthLinkGenerateRequest request) {
        String url = String.format(PATTERN_AUTH_URL,
                request.getAppId(),
                request.getComponentAppId(),
                request.getCopyWxVerify(),
                URLEncoder.encode(request.getRedirectUri(), "UTF-8")
        );
        OfficialAccountAuthLinkGenerateResponse response = new OfficialAccountAuthLinkGenerateResponse();
        response.setLinkUrl(url);
        return response;
    }
}
