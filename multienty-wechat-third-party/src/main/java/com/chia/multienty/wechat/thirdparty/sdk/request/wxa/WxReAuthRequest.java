package com.chia.multienty.wechat.thirdparty.sdk.request.wxa;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.wxa.WxAuthData;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxReAuthResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 小程序认证重新提审
 */
@Data
@WxApi(url = MerchantApis.WxAuth.RE_AUTH_MINI_PROGRAM)
public class WxReAuthRequest extends AuthorizerBaseRequest<WxReAuthResponse> {
    /**
     * 认证数据
     */
    @JsonProperty("auth_data")
    private WxAuthData authData;
}
