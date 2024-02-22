package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.SupportVersionGetResponse;
import lombok.Data;

/**
 * 查询各版本用户占比
 */
@Data
@WxApi(url = MerchantApis.Code.GET_SUPPORT_VERSION)
public class SupportVersionGetRequest extends AuthorizerBaseRequest<SupportVersionGetResponse> {
}
