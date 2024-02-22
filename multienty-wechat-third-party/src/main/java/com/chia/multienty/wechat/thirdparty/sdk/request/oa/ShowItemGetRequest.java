package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.ShowItemGetResponse;
import lombok.Data;

/**
 * 获取已设置公众号信息
 */
@Data
@WxApi(url = MerchantApis.OfficialAccount.GET_SHOW_ITEM, method = WxApi.Method.GET)
public class ShowItemGetRequest extends AuthorizerBaseRequest<ShowItemGetResponse> {
}
