package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.LatestAuditStatusGetResponse;
import lombok.Data;

/**
 * 查询最新一次审核单状态
 */
@Data
@WxApi(url = MerchantApis.Code.GET_LATEST_AUDIT_STATUS, method = WxApi.Method.GET)
public class LatestAuditStatusGetRequest extends AuthorizerBaseRequest<LatestAuditStatusGetResponse> {
}
