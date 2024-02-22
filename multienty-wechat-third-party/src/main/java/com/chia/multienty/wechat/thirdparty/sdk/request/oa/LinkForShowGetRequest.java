package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.LinkForShowGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import lombok.Data;

/**
 * 获取可设置公众号列表
 */
@Data
@WxApi(url = MerchantApis.OfficialAccount.GET_LINK_FOR_SHOW, method = WxApi.Method.GET)
public class LinkForShowGetRequest extends AuthorizerBaseRequest<LinkForShowGetResponse> {
    /**
     * 页码，从 0 开始
     */
    private Integer page;
    /**
     * 每页记录数，最大为 20
     */
    private Integer num;
}
