package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.GrayReleasePlanGetResponse;
import lombok.Data;

/**
 * 获取分阶段发布详情
 */
@Data
@WxApi(url = MerchantApis.Code.GET_GRAY_RELEASE_PLAN, method = WxApi.Method.GET)
public class GrayReleasePlanGetRequest extends AuthorizerBaseRequest<GrayReleasePlanGetResponse> {

}
