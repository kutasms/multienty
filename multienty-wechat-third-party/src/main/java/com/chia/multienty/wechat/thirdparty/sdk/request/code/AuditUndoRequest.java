package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.AuditUndoResponse;
import lombok.Data;

/**
 * 撤回代码审核
 */
@Data
@WxApi(url = MerchantApis.Code.UNDO_AUDIT, method = WxApi.Method.GET)
public class AuditUndoRequest extends AuthorizerBaseRequest<AuditUndoResponse> {
}
