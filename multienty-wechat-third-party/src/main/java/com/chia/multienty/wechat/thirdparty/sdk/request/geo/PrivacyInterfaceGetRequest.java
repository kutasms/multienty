package com.chia.multienty.wechat.thirdparty.sdk.request.geo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.geo.PrivacyInterfaceGetResponse;
import lombok.Data;

/**
 * 获取地理位置接口列表
 */
@Data
@WxApi(url = MerchantApis.GEO.GET_PRIVACY_INTERFACE, method = WxApi.Method.GET)
public class PrivacyInterfaceGetRequest extends AuthorizerBaseRequest<PrivacyInterfaceGetResponse> {
}
