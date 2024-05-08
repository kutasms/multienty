package com.chia.multienty.wechat.thirdparty.sdk.request.member;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterGetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 获取体验者列表
 */
@Data
@WxApi(url = MerchantApis.Member.GET_TESTER)
public class TesterGetRequest extends AuthorizerBaseRequest<TesterGetResponse> {
    /**
     * 填 "get_experiencer" 即可
     */
    @JsonProperty("action")
    private String action;
}
