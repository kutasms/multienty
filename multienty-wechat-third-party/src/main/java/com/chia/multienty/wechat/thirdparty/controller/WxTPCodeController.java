package com.chia.multienty.wechat.thirdparty.controller;

import com.chia.multienty.core.annotation.ValidateHeader;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultiTenantHeaderConstants;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.wechat.thirdparty.parameter.code.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.*;
import com.chia.multienty.wechat.thirdparty.service.WxTPCodeService;
import com.chia.multienty.wechat.thirdparty.parameter.code.*;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 微信第三方平台代码管理前端控制器
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-05
 */
@RestController
@RequestMapping("/wx/tp/code")
@RequiredArgsConstructor
@Api(tags = "微信第三方平台代码管理前端控制器")
public class WxTPCodeController {
    private final WxTPCodeService wxTPCodeService;

    @ApiOperation("提交代码")
    @PostMapping("commit")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<CommitResponse> commit(@RequestBody CommitParameter parameter) {
        CommitResponse response = wxTPCodeService.commit(MultiTenantContext.getMppAppId(), parameter.getTemplate());
        return new Result<>(response);
    }
    @ApiOperation("撤回代码审核")
    @PostMapping("undoAudit")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<AuditUndoResponse> undoAudit() {
        AuditUndoResponse response = wxTPCodeService.undoAudit(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("查询审核单状态")
    @PostMapping("getAuditStatus")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<AuditStatusGetResponse> getAuditStatus(@RequestBody AuditStatusGetParameter parameter) {
        AuditStatusGetResponse response = wxTPCodeService.getAuditStatus(
                MultiTenantContext.getMppAppId(), parameter.getAuditId());
        return new Result<>(response);
    }
    @ApiOperation("查询服务商审核额度")
    @PostMapping("setCodeAuditQuota")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<CodeAuditQuotaSetResponse> setCodeAuditQuota() {
        CodeAuditQuotaSetResponse response = wxTPCodeService.setCodeAuditQuota(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("加急代码审核")
    @PostMapping("speedupCodeAudit")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<CodeAuditSpeedupResponse> speedupCodeAudit(@RequestBody CodeAuditSpeedupParameter parameter) {
        CodeAuditSpeedupResponse response = wxTPCodeService.speedupCodeAudit(
                MultiTenantContext.getMppAppId(), parameter.getAuditId());
        return new Result<>(response);
    }
    @ApiOperation("获取已上传的代码页面列表")
    @PostMapping("getCodePage")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<CodePageGetResponse> getCodePage() {
        CodePageGetResponse response = wxTPCodeService.getCodePage(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("获取隐私接口检测结果")
    @PostMapping("getCodePrivacyInfo")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<CodePrivacyInfoGetResponse> getCodePrivacyInfo() {
        CodePrivacyInfoGetResponse response = wxTPCodeService.getCodePrivacyInfo(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("小程序版本回退")
    @PostMapping("revertCodeRelease")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<CodeReleaseRevertResponse> revertCodeRelease(@RequestBody CodeReleaseRevertParameter parameter) {
        CodeReleaseRevertResponse response = wxTPCodeService.revertCodeRelease(MultiTenantContext.getMppAppId(),
                parameter.getAppVersion());
        return new Result<>(response);
    }
    @ApiOperation("获取分阶段发布详情")
    @PostMapping("getGrayReleasePlan")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<GrayReleasePlanGetResponse> getGrayReleasePlan() {
        GrayReleasePlanGetResponse response = wxTPCodeService.getGrayReleasePlan(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("分阶段发布")
    @PostMapping("grayRelease")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<GrayReleaseResponse> grayRelease(@RequestBody GrayReleaseParameter parameter) {
        GrayReleaseResponse response = wxTPCodeService.grayRelease(MultiTenantContext.getMppAppId(),
                parameter.getPercentage(),
                parameter.getDebugerFirst(),
                parameter.getMemberFirst());
        return new Result<>(response);
    }
    @ApiOperation("取消分阶段发布")
    @PostMapping("revertGrayRelease")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<GrayReleaseRevertResponse> revertGrayRelease() {
        GrayReleaseRevertResponse response = wxTPCodeService.revertGrayRelease(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("查询最新一次审核单状态")
    @PostMapping("getLatestAuditStatus")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<LatestAuditStatusGetResponse> getLatestAuditStatus() {
        LatestAuditStatusGetResponse response = wxTPCodeService.getLatestAuditStatus(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("上传提审素材")
    @PostMapping("uploadMediaToCodeAudit")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<MediaToCodeAuditUploadResponse> uploadMediaToCodeAudit(MultipartFile file) {
        MediaToCodeAuditUploadResponse response = wxTPCodeService.uploadMediaToCodeAudit(MultiTenantContext.getMppAppId(), file);
        return new Result<>(response);
    }
    @ApiOperation("发布已通过审核的小程序")
    @PostMapping("release")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<ReleaseResponse> release() {
        ReleaseResponse response = wxTPCodeService.release(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("查询各版本用户占比")
    @PostMapping("getSupportVersion")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<SupportVersionGetResponse> getSupportVersion() {
        SupportVersionGetResponse response = wxTPCodeService.getSupportVersion(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("设置最低基础库版本")
    @PostMapping("setSupportVersion")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<SupportVersionSetResponse> setSupportVersion(@RequestBody SupportVersionSetParameter parameter) {
        SupportVersionSetResponse response = wxTPCodeService.setSupportVersion(MultiTenantContext.getMppAppId(), parameter.getVersion());
        return new Result<>(response);
    }
    @ApiOperation("获取体验版二维码")
    @PostMapping("getTrialQRCode")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<TrialQRCodeGetResponse> getTrialQRCode(@RequestBody TrialQRCodeGetParameter parameter) {
        TrialQRCodeGetResponse response = wxTPCodeService.getTrialQRCode(MultiTenantContext.getMppAppId(), parameter.getPath());
        return new Result<>(response);
    }
    @ApiOperation("提交代码审核")
    @PostMapping("submitAudit")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<AuditSubmitResponse> submitAudit(@RequestBody AuditSubmitParameter parameter) {
        AuditSubmitResponse response = wxTPCodeService.submitAudit(MultiTenantContext.getMppAppId(),
                parameter.getFeedbackInfo(), parameter.getItemList());
        return new Result<>(response);
    }
    @ApiOperation("查询小程序版本信息")
    @PostMapping("getVersionInfo")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<VersionInfoGetResponse> getVersionInfo() {
        VersionInfoGetResponse response = wxTPCodeService.getVersionInfo(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("查询小程序服务状态")
    @PostMapping("getVisitStatus")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<VisitStatusGetResponse> getVisitStatus() {
        VisitStatusGetResponse response = wxTPCodeService.getVisitStatus(MultiTenantContext.getMppAppId());
        return new Result<>(response);
    }
    @ApiOperation("设置小程序服务状态")
    @PostMapping("setVisitStatus")
    @ValidateHeader(headNames = MultiTenantHeaderConstants.MPP_APP_ID_KEY)
    public Result<VisitStatusSetResponse> setVisitStatus(@RequestBody VisitStatusSetParameter parameter) {
        VisitStatusSetResponse response = wxTPCodeService.setVisitStatus(MultiTenantContext.getMppAppId(), parameter.getAction());
        return new Result<>(response);
    }
}
