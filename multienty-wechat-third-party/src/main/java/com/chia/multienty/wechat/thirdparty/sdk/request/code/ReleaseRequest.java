package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.ReleaseResponse;
import lombok.Data;

/**
 * 发布已通过审核的小程序
 */
@Data
@WxApi(url = MerchantApis.Code.RELEASE)
public class ReleaseRequest extends AuthorizerBaseRequest<ReleaseResponse> {
}
