package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.pojo.WechatMppNickNameAudit;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.service.WechatMppNickNameAuditService;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.wechat.thirdparty.define.oa.WxMedia;
import com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo.*;
import com.chia.multienty.wechat.thirdparty.sdk.request.oa.TempAssetAddRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.TempAssetAddResponse;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import com.chia.multienty.wechat.thirdparty.service.WxTPBaseInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class WxTPBaseInfoServiceImpl implements WxTPBaseInfoService {
    private final WxTPApiExecutor executor;
    private final YamlMultientyProperties properties;
    private final WechatAppService wechatAppService;
    private final ObjectMapper objectMapper;

    private final WechatMppNickNameAuditService wechatMppNickNameAuditService;

    @Override
    public AccountBasicInfoGetResponse getAccountBasicInfo(String appId) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        AccountBasicInfoGetRequest request = new AccountBasicInfoGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        AccountBasicInfoGetResponse response = executor.execute(request);
        return response;
    }

    @Override
    public BindOpenAccountGetResponse getBindOpenAccount(String appId) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        BindOpenAccountGetRequest request = new BindOpenAccountGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public HeadImageSetResponse setHeadImage(String appId, MultipartFile file) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        TempAssetAddRequest assetAddRequest = new TempAssetAddRequest();
        assetAddRequest.setFile(file);
        assetAddRequest.setAccessToken(app.getAuthorizerAccessToken());
        assetAddRequest.setType("image");
        TempAssetAddResponse assetAddResponse = executor.execute(assetAddRequest);

        HeadImageSetRequest request = new HeadImageSetRequest();

        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setX1("0");
        request.setY1("0");
        request.setX2("1");
        request.setY2("1");
        request.setHeadImgMediaId(assetAddResponse.getMediaId());
        return executor.execute(request);
    }

    @Override
    @SneakyThrows
    public LoginResponse login(String appId, String jsCode) {
        LoginRequest request = new LoginRequest();
        request.setAppId(appId);
        request.setJsCode(jsCode);
        request.setGrantType("authorization_code");
        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        log.info("微信第三方登录参数:{}", objectMapper.writeValueAsString(request));
        return executor.execute(request);
    }

    @Override
    public NickNameCheckResponse checkNickName(String appId, String nickName) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        NickNameCheckRequest request = new NickNameCheckRequest();
        request.setNickName(nickName);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public NickNameSetResponse setNickName(String appId, String nickName, Map<String, WxMedia> mediaMap) {
        NickNameSetRequest request = new NickNameSetRequest();
        request.setNickName(nickName);
        request.setLicense(mediaMap.get("license").getMediaId());

        NickNameSetResponse response = executor.execute(request);
        WechatMppNickNameAudit audit = new WechatMppNickNameAudit()
                .setNickName(nickName)
                .setAuditId(response.getAuditId())
                .setStatus(StatusEnum.WAITING.getCode())
                .setAppId(appId)
                .setTenantId(MultientyContext.getTenant().getTenantId())
                .setWording(response.getWording());
        wechatMppNickNameAuditService.saveTE(audit);
        return response;
    }

    @Override
    public NickNameStatusGetResponse getNickNameStatus(String appId, Long auditId) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        NickNameStatusGetRequest request = new NickNameStatusGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setAuditId(auditId);
        return executor.execute(request);
    }

    @Override
    public SearchStatusGetResponse getSearchStatus(String appId) {
        SearchStatusGetRequest request = new SearchStatusGetRequest();
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public SearchStatusSetResponse setSearchStatus(String appId, Integer status) {
        SearchStatusSetRequest request = new SearchStatusSetRequest();
        request.setStatus(status);
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public SignatureSetResponse setSignature(String appId, String signature) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        SignatureSetRequest request = new SignatureSetRequest();
        request.setSignature(signature);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }
}
