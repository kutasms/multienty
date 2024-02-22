package com.chia.multienty.wechat.thirdparty.sdk.request.member;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterUnbindResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 解除绑定体验者
 */
@Data
@WxApi(url = MerchantApis.Member.UNBIND_TESTER)
public class TesterUnbindRequest extends AuthorizerBaseRequest<TesterUnbindResponse> {
    /**
     * 微信号。 userstr 和 wechatid 填写其中一个即可
     */
    @JsonProperty(value = "wechatid")
    private String wechatId;
    /**
     * 人员对应的唯一字符串， 可通过getTester接口获取人员对应的字符串
     */
    @JsonProperty(value = "userstr")
    private String userStr;
}
