package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.wechat.WechatMppCodeAuditUpdateParameter;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.pojo.WechatMppCodeAudit;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.service.WechatMppCodeAuditService;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.wechat.thirdparty.define.code.CodeAuditItem;
import com.chia.multienty.wechat.thirdparty.parameter.code.CommitParameter;
import com.chia.multienty.wechat.thirdparty.sdk.request.code.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizerAccessTokenGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPCodeTool;
import com.chia.multienty.wechat.thirdparty.service.WxTPCodeService;
import com.chia.multienty.wechat.thirdparty.service.WxTPPlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WxTPCodeServiceImpl implements WxTPCodeService {
    private final WxTPApiExecutor executor;
    private final WechatAppService wechatAppService;

    private final WechatMppCodeAuditService wechatMppCodeAuditService;

    private final WxTPPlatformService wxTPPlatformService;

    private final WxTPCodeTool wxTPCodeTool;

    @Override
    public CommitResponse commit(CommitParameter parameter) {
        WechatApp app = wechatAppService.getByIdAndSharding(new WechatApp()
                .setTenantId(MultientyContext.getTenant().getTenantId())
                .setProgramId(parameter.getProgramId())
        );
        CommitResponse response = wxTPCodeTool.uploadCode(app, parameter);
        return response;
    }

    @Override
    public AuditUndoResponse undoAudit(String appId) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        WechatMppCodeAudit audit = wechatMppCodeAuditService.getByAppId(appId);
        if(audit != null) {
            audit.setStatus(StatusEnum.CANCELLED.getCode());
            audit.setReason("商家主动撤回");
            WechatMppCodeAuditUpdateParameter parameter = new WechatMppCodeAuditUpdateParameter();
            BeanUtils.copyProperties(audit, parameter);
            wechatMppCodeAuditService.update(parameter);
            AuditUndoRequest request = new AuditUndoRequest();
            request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
            AuditUndoResponse response = executor.execute(request);
            return response;
        }
        throw new KutaRuntimeException(HttpResultEnum.SYSTEM_STATUS_ERROR);
    }

    @Override
    public AuditStatusGetResponse getAuditStatus(String appId, Long auditId) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        AuditStatusGetRequest request = new AuditStatusGetRequest();
        request.setAuditId(auditId);
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public CodeAuditQuotaSetResponse setCodeAuditQuota() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        CodeAuditQuotaSetRequest request = new CodeAuditQuotaSetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public CodeAuditSpeedupResponse speedupCodeAudit(String appId, Long auditId) {
        CodeAuditSpeedupRequest request = new CodeAuditSpeedupRequest();
        request.setAuditId(auditId);
        return executor.execute(request);
    }

    @Override
    public CodePageGetResponse getCodePage() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        CodePageGetRequest request = new CodePageGetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public CodePrivacyInfoGetResponse getCodePrivacyInfo() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        CodePrivacyInfoGetRequest request = new CodePrivacyInfoGetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public CodeReleaseRevertResponse revertCodeRelease(String appVersion) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        CodeReleaseRevertRequest request = new CodeReleaseRevertRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        request.setAction("get_history_version");
        request.setAppVersion(appVersion);
        return executor.execute(request);
    }

    @Override
    public GrayReleasePlanGetResponse getGrayReleasePlan() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        GrayReleasePlanGetRequest request = new GrayReleasePlanGetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public GrayReleaseResponse grayRelease(Integer percentage, Boolean debugerFirst, Boolean memberFirst) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        GrayReleaseRequest request = new GrayReleaseRequest();
        request.setGrayPercentage(percentage);
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        request.setSupportDebugerFirst(debugerFirst);
        request.setSupportExperiencerFirst(memberFirst);
        return executor.execute(request);
    }

    @Override
    public GrayReleaseRevertResponse revertGrayRelease() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        GrayReleaseRevertRequest request = new GrayReleaseRevertRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public LatestAuditStatusGetResponse getLatestAuditStatus() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        LatestAuditStatusGetRequest request = new LatestAuditStatusGetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public MediaToCodeAuditUploadResponse uploadMediaToCodeAudit(MultipartFile file) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        MediaToCodeAuditUploadRequest request = new MediaToCodeAuditUploadRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        request.setFile(file);
        return executor.execute(request);
    }

    @Override
    public ReleaseResponse release() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        ReleaseRequest request = new ReleaseRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public SupportVersionGetResponse getSupportVersion() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        SupportVersionGetRequest request = new SupportVersionGetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public SupportVersionSetResponse setSupportVersion(String version) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        SupportVersionSetRequest request = new SupportVersionSetRequest();
        request.setVersion(version);
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public TrialQRCodeGetResponse getTrialQRCode(String path) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());

        TrialQRCodeGetRequest request = new TrialQRCodeGetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        request.setPath(path);
        TrialQRCodeGetResponse response = executor.execute(request);
        byte[] encode = Base64.getEncoder().encode(response.getStream());
        response.setQrcode(new String(encode));
        return response;
    }

    @Override
    public AuditSubmitResponse submitAudit(String feedbackInfo, List<CodeAuditItem> itemList) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        AuditSubmitRequest request = new AuditSubmitRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        request.setItemList(itemList);
        request.setFeedbackInfo(feedbackInfo);

        AuditSubmitResponse response = executor.execute(request);
        WechatMppCodeAudit audit = new WechatMppCodeAudit();
        audit.setAuditId(response.getAuditId());
        audit.setErrorCode(response.getErrCode());
        audit.setErrorMsg(response.getErrMsg());
        audit.setAppId(MultientyContext.getMppAppId());
        audit.setTenantId(MultientyContext.getTenant().getTenantId());
        wechatMppCodeAuditService.saveTE(audit);
        return response;
    }

    @Override
    public VersionInfoGetResponse getVersionInfo() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        VersionInfoGetRequest request = new VersionInfoGetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public VisitStatusGetResponse getVisitStatus() {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        VisitStatusGetRequest request = new VisitStatusGetRequest();
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }

    @Override
    public VisitStatusSetResponse setVisitStatus(String action) {
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(MultientyContext.getTenant().getTenantId(), MultientyContext.getProgramId());
        VisitStatusSetRequest request = new VisitStatusSetRequest();
        request.setAction(action);
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }
}
