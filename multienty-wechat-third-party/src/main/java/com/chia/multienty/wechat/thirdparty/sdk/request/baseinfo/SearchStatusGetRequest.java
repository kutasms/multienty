package com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.SearchStatusGetResponse;
import lombok.Data;

/**
 * 获取搜索状态
 */
@Data
@WxApi(url = MerchantApis.BaseInfo.GET_SEARCH_STATUS, method = WxApi.Method.GET)
public class SearchStatusGetRequest extends AuthorizerBaseRequest<SearchStatusGetResponse> {
}
