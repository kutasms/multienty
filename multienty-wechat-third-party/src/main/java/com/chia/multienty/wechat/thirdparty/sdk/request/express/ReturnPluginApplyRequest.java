package com.chia.multienty.wechat.thirdparty.sdk.request.express;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.express.ReturnPluginApplyResponse;
import lombok.Data;

/**
 * 申请开通物流退货组件
 */
@Data
@WxApi(url = MerchantApis.Express.APPLY_RETURN_PLUGIN)
public class ReturnPluginApplyRequest extends AuthorizerBaseRequest<ReturnPluginApplyResponse> {

}
