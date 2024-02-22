package com.chia.multienty.wechat.thirdparty.sdk.request.express;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.express.MsgPluginApplyResponse;
import lombok.Data;

/**
 * 申请开通物流消息
 */
@Data
@WxApi(url = MerchantApis.Express.APPLY_MSG_PLUGIN)
public class MsgPluginApplyRequest extends AuthorizerBaseRequest<MsgPluginApplyResponse> {

}
