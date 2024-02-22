package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.pojo.WechatMppRegisterAudit;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.service.WechatMppRegisterAuditService;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.define.wxa.WxAuthData;
import com.chia.multienty.wechat.thirdparty.sdk.request.wxa.WxAuthMaterialUploadRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.wxa.WxAuthProgressQueryRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.wxa.WxAuthRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.wxa.WxReAuthRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthMaterialUploadResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthProgressQueryResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxReAuthResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPWxaService;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class WxTPWxaServiceImpl implements WxTPWxaService {
    private final WxTPApiExecutor executor;
    private final WechatAppService wechatAppService;
    private final WechatMppRegisterAuditService wechatMppRegisterAuditService;
    @Override
    public WxAuthMaterialUploadResponse uploadWxAuthMaterial(MultipartFile file, String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        WxAuthMaterialUploadRequest request = new WxAuthMaterialUploadRequest();
        request.setFile(file);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public WxAuthProgressQueryResponse queryWxAuthProgress(String appId, String taskId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        WxAuthProgressQueryRequest request = new WxAuthProgressQueryRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setTaskId(taskId);
        return executor.execute(request);
    }

    @Override
    public WxAuthResponse authMiniProgram(String appId, WxAuthData authData) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        WxAuthRequest request = new WxAuthRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setAuthData(authData);
        WxAuthResponse response = executor.execute(request);
        WechatMppRegisterAudit audit = new WechatMppRegisterAudit();
        audit.setTenantId(MultiTenantContext.getTenant().getTenantId());
        audit.setStatus(StatusEnum.WAITING.getCode());
        audit.setAppId(appId);
        audit.setErrorCode(response.getErrCode());
        audit.setErrorMsg(response.getErrMsg());
        audit.setTaskId(response.getTaskId());
        audit.setStatus(StatusEnum.WAITING.getCode());
        audit.setAuthUrl(response.getAuthUrl());
        wechatMppRegisterAuditService.saveTE(audit);
        return response;
    }

    @Override
    public WxReAuthResponse reAuthMiniProgram(String appId, WxAuthData authData) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        WxReAuthRequest request = new WxReAuthRequest();
        request.setAuthData(authData);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }
}
