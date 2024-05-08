package com.chia.multienty.wechat.thirdparty.sdk.request.member;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterBindResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 绑定体验者
 */
@Data
@WxApi(url = MerchantApis.Member.BIND_TESTER)
public class TesterBindRequest extends AuthorizerBaseRequest<TesterBindResponse> {
    /**
     * 微信号
     */
    @JsonProperty(value = "wechatid")
    private String wechatId;
}
