package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.VisitStatusGetResponse;
import lombok.Data;

/**
 * 查询小程序服务状态
 */
@Data
@WxApi(url = MerchantApis.Code.GET_VISIT_STATUS)
public class VisitStatusGetRequest extends AuthorizerBaseRequest<VisitStatusGetResponse> {
}
