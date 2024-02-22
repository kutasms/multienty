package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.SupportVersionSetResponse;
import lombok.Data;

/**
 * 设置最低基础库版本
 */
@Data
@WxApi(url = MerchantApis.Code.SET_SUPPORT_VERSION)
public class SupportVersionSetRequest extends AuthorizerBaseRequest<SupportVersionSetResponse> {
    /**
     * 为已发布的基础库版本号
     */
    private String version;
}
