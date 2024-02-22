package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.pojo.WechatMppCodeAudit;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.service.WechatMppCodeAuditService;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.define.code.CodeAuditItem;
import com.chia.multienty.wechat.thirdparty.define.template.MppCodeTemplate;
import com.chia.multienty.wechat.thirdparty.sdk.request.code.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.*;
import com.chia.multienty.wechat.thirdparty.service.WxTPCodeService;
import com.chia.multienty.wechat.thirdparty.sdk.request.code.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.*;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPCodeTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WxTPCodeServiceImpl implements WxTPCodeService {
    private final WxTPApiExecutor executor;
    private final WechatAppService wechatAppService;

    private final WechatMppCodeAuditService wechatMppCodeAuditService;

    private final WxTPCodeTool wxTPCodeTool;

    @Override
    public CommitResponse commit(String appId, MppCodeTemplate template) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        CommitResponse response = wxTPCodeTool.uploadCode(app, template);
        return response;
    }

    @Override
    public AuditUndoResponse undoAudit(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        WechatMppCodeAudit audit = wechatMppCodeAuditService.getByAppId(appId);
        if(audit != null) {
            audit.setStatus(StatusEnum.CANCELLED.getCode());
            audit.setReason("商家主动撤回");
            wechatMppCodeAuditService.updateByIdTE(audit);
            AuditUndoRequest request = new AuditUndoRequest();
            request.setAccessToken(app.getAuthorizerAccessToken());
            AuditUndoResponse response = executor.execute(request);
            return response;
        }
        throw new KutaRuntimeException(HttpResultEnum.SYSTEM_STATUS_ERROR);
    }

    @Override
    public AuditStatusGetResponse getAuditStatus(String appId, Long auditId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        AuditStatusGetRequest request = new AuditStatusGetRequest();
        request.setAuditId(auditId);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public CodeAuditQuotaSetResponse setCodeAuditQuota(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        CodeAuditQuotaSetRequest request = new CodeAuditQuotaSetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public CodeAuditSpeedupResponse speedupCodeAudit(String appId, Long auditId) {
        CodeAuditSpeedupRequest request = new CodeAuditSpeedupRequest();
        request.setAuditId(auditId);
        return executor.execute(request);
    }

    @Override
    public CodePageGetResponse getCodePage(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        CodePageGetRequest request = new CodePageGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public CodePrivacyInfoGetResponse getCodePrivacyInfo(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        CodePrivacyInfoGetRequest request = new CodePrivacyInfoGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public CodeReleaseRevertResponse revertCodeRelease(String appId, String appVersion) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        CodeReleaseRevertRequest request = new CodeReleaseRevertRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setAction("get_history_version");
        request.setAppVersion(appVersion);
        return executor.execute(request);
    }

    @Override
    public GrayReleasePlanGetResponse getGrayReleasePlan(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        GrayReleasePlanGetRequest request = new GrayReleasePlanGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public GrayReleaseResponse grayRelease(String appId, Integer percentage, Boolean debugerFirst, Boolean memberFirst) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        GrayReleaseRequest request = new GrayReleaseRequest();
        request.setGrayPercentage(percentage);
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setSupportDebugerFirst(debugerFirst);
        request.setSupportExperiencerFirst(memberFirst);
        return executor.execute(request);
    }

    @Override
    public GrayReleaseRevertResponse revertGrayRelease(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        GrayReleaseRevertRequest request = new GrayReleaseRevertRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public LatestAuditStatusGetResponse getLatestAuditStatus(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        LatestAuditStatusGetRequest request = new LatestAuditStatusGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public MediaToCodeAuditUploadResponse uploadMediaToCodeAudit(String appId, MultipartFile file) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        MediaToCodeAuditUploadRequest request = new MediaToCodeAuditUploadRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setFile(file);
        return executor.execute(request);
    }

    @Override
    public ReleaseResponse release(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        ReleaseRequest request = new ReleaseRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public SupportVersionGetResponse getSupportVersion(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        SupportVersionGetRequest request = new SupportVersionGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public SupportVersionSetResponse setSupportVersion(String appId, String version) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        SupportVersionSetRequest request = new SupportVersionSetRequest();
        request.setVersion(version);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public TrialQRCodeGetResponse getTrialQRCode(String appId, String path) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        TrialQRCodeGetRequest request = new TrialQRCodeGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setPath(path);
        return executor.execute(request);
    }

    @Override
    public AuditSubmitResponse submitAudit(String appId, String feedbackInfo, List<CodeAuditItem> itemList) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        AuditSubmitRequest request = new AuditSubmitRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        request.setItemList(itemList);
        request.setFeedbackInfo(feedbackInfo);

        AuditSubmitResponse response = executor.execute(request);
        WechatMppCodeAudit audit = new WechatMppCodeAudit();
        audit.setAuditId(response.getAuditId());
        audit.setErrorCode(response.getErrCode());
        audit.setErrorMsg(response.getErrMsg());
        audit.setAppId(app.getMiniAppId());
        audit.setTenantId(MultiTenantContext.getTenant().getTenantId());
        wechatMppCodeAuditService.saveTE(audit);
        return response;
    }

    @Override
    public VersionInfoGetResponse getVersionInfo(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        VersionInfoGetRequest request = new VersionInfoGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public VisitStatusGetResponse getVisitStatus(String appId) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        VisitStatusGetRequest request = new VisitStatusGetRequest();
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public VisitStatusSetResponse setVisitStatus(String appId, String action) {
        WechatApp app = wechatAppService.getBy(MultiTenantContext.getTenant().getTenantId(), appId);
        VisitStatusSetRequest request = new VisitStatusSetRequest();
        request.setAction(action);
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }
}
