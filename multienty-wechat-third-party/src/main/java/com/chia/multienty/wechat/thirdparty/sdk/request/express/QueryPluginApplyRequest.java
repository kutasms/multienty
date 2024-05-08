package com.chia.multienty.wechat.thirdparty.sdk.request.express;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.express.QueryPluginApplyResponse;
import lombok.Data;

/**
 * 申请开通物流查询组件响应数据
 */
@Data
@WxApi(url = MerchantApis.Express.APPLY_QUERY_PLUGIN)
public class QueryPluginApplyRequest extends AuthorizerBaseRequest<QueryPluginApplyResponse> {
}
