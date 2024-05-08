package com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.SignatureSetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 设置小程序介绍
 */
@Data
@WxApi(url = MerchantApis.BaseInfo.SET_SIGNATURE)
public class SignatureSetRequest extends AuthorizerBaseRequest<SignatureSetResponse> {
    /**
     * 功能介绍（简介）
     */
    @JsonProperty("signature")
    private String signature;
}
