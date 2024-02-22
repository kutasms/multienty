package com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.SearchStatusSetResponse;
import lombok.Data;

/**
 * 设置搜索状态
 */
@Data
@WxApi(url = MerchantApis.BaseInfo.SET_SEARCH_STATUS)
public class SearchStatusSetRequest extends AuthorizerBaseRequest<SearchStatusSetResponse> {
    /**
     * 1 表示不可搜索，0 表示可搜索
     */
    private Integer status;
}
