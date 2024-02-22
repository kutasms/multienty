package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.CodeAuditQuotaSetResponse;
import lombok.Data;

/**
 * 查询服务商审核额度
 */
@Data
@WxApi(url = MerchantApis.Code.SET_CODE_AUDIT_QUOTA, method = WxApi.Method.GET)
public class CodeAuditQuotaSetRequest extends AuthorizerBaseRequest<CodeAuditQuotaSetResponse> {
}
