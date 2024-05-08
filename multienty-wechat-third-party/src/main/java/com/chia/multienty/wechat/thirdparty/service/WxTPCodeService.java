package com.chia.multienty.wechat.thirdparty.service;

import com.chia.multienty.wechat.thirdparty.define.code.CodeAuditItem;
import com.chia.multienty.wechat.thirdparty.parameter.code.CommitParameter;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WxTPCodeService {

    /**
     * 上传代码并生成体验版
     * @return
     */
    CommitResponse commit(CommitParameter parameter);

    /**
     * 撤回代码审核
     * @param appId 商家小程序编号
     * @return
     */
    AuditUndoResponse undoAudit(String appId);

    /**
     * 查询审核单状态
     * @param appId 商家小程序编号
     * @param auditId 审核编号
     * @return
     */
    AuditStatusGetResponse getAuditStatus(String appId, Long auditId);


    /**
     * 查询服务商审核额度
     * @return
     */
    CodeAuditQuotaSetResponse setCodeAuditQuota();

    /**
     * 加急代码审核
     * @param appId 商家小程序编号
     * @param auditId 审核编号
     * @return
     */
    CodeAuditSpeedupResponse speedupCodeAudit(String appId, Long auditId);


    /**
     * 获取已上传的代码页面列表
     * @return
     */
    CodePageGetResponse getCodePage();


    /**
     * 获取隐私接口检测结果
     * @return
     */
    CodePrivacyInfoGetResponse getCodePrivacyInfo();


    /**
     * 小程序版本回退
     * @param appVersion 应用版本
     * @return
     */
    CodeReleaseRevertResponse revertCodeRelease(String appVersion);

    /**
     * 获取分阶段发布详情
     * @return
     */
    GrayReleasePlanGetResponse getGrayReleasePlan();

    /**
     * 分阶段发布
     * @param percentage 灰度比例
     * @param debugerFirst 是否对测试人员有效
     * @param memberFirst 是否对项目成员有效
     * @return
     */
    GrayReleaseResponse grayRelease(Integer percentage, Boolean debugerFirst, Boolean memberFirst);

    /**
     * 查询最新一次审核单状态
     * @return
     */
    GrayReleaseRevertResponse revertGrayRelease();

    /**
     *
     * @return
     */
    LatestAuditStatusGetResponse getLatestAuditStatus();

    /**
     * 上传提审素材
     * @param file 商家上传的文件
     * @return
     */
    MediaToCodeAuditUploadResponse uploadMediaToCodeAudit(MultipartFile file);

    /**
     * 发布已通过审核的小程序
     * @return
     */
    ReleaseResponse release();

    /**
     * 查询各版本用户占比
     * @return
     */
    SupportVersionGetResponse getSupportVersion();

    /**
     * 设置最低基础库版本
     * @param version 版本
     * @return
     */
    SupportVersionSetResponse setSupportVersion(String version);

    /**
     * 获取体验版二维码
     * @param path 路径
     * @return
     */
    TrialQRCodeGetResponse getTrialQRCode(String path);

    /**
     * 提交代码审核
     * @param feedbackInfo  反馈信息
     * @param itemList 审核项目列表
     * @return
     */
    AuditSubmitResponse submitAudit(String feedbackInfo, List<CodeAuditItem> itemList);

    /**
     * 查询小程序版本信息
     * @return
     */
    VersionInfoGetResponse getVersionInfo();

    /**
     * 查询小程序服务状态
     * @return
     */
    VisitStatusGetResponse getVisitStatus();

    /**
     * 设置小程序服务状态
     * @param action 操作 open/close
     * @return
     */
    VisitStatusSetResponse setVisitStatus(String action);
}
