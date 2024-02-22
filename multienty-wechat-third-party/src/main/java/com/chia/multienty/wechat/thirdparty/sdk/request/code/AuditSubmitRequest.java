package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.define.code.CodeAuditItem;
import com.chia.multienty.wechat.thirdparty.define.code.CodeAuditPreviewInfo;
import com.chia.multienty.wechat.thirdparty.define.code.CodeAuditUGCDeclare;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.AuditSubmitResponse;
import lombok.Data;

import java.util.List;

/**
 * 提交代码审核
 */
@Data
@WxApi(url = MerchantApis.Code.SUBMIT_AUDIT)
public class AuditSubmitRequest extends AuthorizerBaseRequest<AuditSubmitResponse> {
    /**
     * 审核项列表（选填，至多填写 5 项）；类目是必填的，且要填写已经在小程序配置好的类目
     */
    private List<CodeAuditItem> itemList;
    /**
     * 反馈内容，至多 200 字
     */
    private String feedbackInfo;
    /**
     * 用 | 分割的 media_id 列表，至多 5 张图片, 可以通过新增临时素材接口上传而得到
     */
    private String feedbackStuff;
    /**
     * 小程序版本说明和功能解释
     */
    private String versionDesc;
    /**
     * 预览信息（小程序页面截图和操作录屏）
     */
    private CodeAuditPreviewInfo previewInfo;
    /**
     * 用户生成内容场景（UGC）信息安全声明
     */
    private CodeAuditUGCDeclare ugcDeclare;
    /**
     * 用于声明是否不使用“代码中检测出但是未配置的隐私相关接口”
     */
    private Boolean privacyApiNotUse;
    /**
     * 订单中心path
     */
    private String orderPath;
}
