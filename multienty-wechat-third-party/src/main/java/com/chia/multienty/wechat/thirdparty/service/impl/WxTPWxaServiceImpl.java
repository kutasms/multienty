package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.pojo.WechatMppAuthTask;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.service.WechatMppAuthTaskService;
import com.chia.multienty.core.service.WechatMppRegisterAuditService;
import com.chia.multienty.core.tools.IdWorkerProvider;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.wechat.thirdparty.define.wxa.WxAuthData;
import com.chia.multienty.wechat.thirdparty.sdk.request.wxa.WxAuthMaterialUploadRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.wxa.WxAuthProgressQueryRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.wxa.WxAuthRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.wxa.WxReAuthRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthMaterialUploadResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthProgressQueryResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxReAuthResponse;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import com.chia.multienty.wechat.thirdparty.service.WxTPWxaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class WxTPWxaServiceImpl implements WxTPWxaService {
    private final WxTPApiExecutor executor;
    private final WechatAppService wechatAppService;
    private final WechatMppRegisterAuditService wechatMppRegisterAuditService;
    private final ObjectMapper objectMapper;

    private final WechatMppAuthTaskService wechatMppAuthTaskService;
    @Override
    public WxAuthMaterialUploadResponse uploadWxAuthMaterial(MultipartFile file, String appId) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        WxAuthMaterialUploadRequest request = new WxAuthMaterialUploadRequest();
        request.setFile(file);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public WxAuthProgressQueryResponse queryWxAuthProgress(String appId, String taskId) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        WxAuthProgressQueryRequest request = new WxAuthProgressQueryRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setTaskId(taskId);
        return executor.execute(request);
    }

    @Override
    @SneakyThrows
    public WxAuthResponse authMiniProgram(String appId, WxAuthData authData) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        WxAuthRequest request = new WxAuthRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setAuthData(authData);

        WechatMppAuthTask task = new WechatMppAuthTask();
        task.setParams(objectMapper.writeValueAsString(request));
        task.setAppId(appId);
        task.setStatus(StatusEnum.WAITING.getCode());
        task.setTaskId(IdWorkerProvider.next());
        task.setTenantId(MultientyContext.getTenant().getTenantId());
        wechatMppAuthTaskService.save(task);
        request.getAuthData().setTaskId(task.getTaskId().toString());
        WxAuthResponse response = executor.execute(request);
        return response;
    }

    @Override
    public WxReAuthResponse reAuthMiniProgram(String appId, WxAuthData authData) {
        WechatApp app = wechatAppService.getBy(MultientyContext.getTenant().getTenantId(), appId);
        WxReAuthRequest request = new WxReAuthRequest();
        request.setAuthData(authData);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }
}
