package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.GrayReleaseRevertResponse;
import lombok.Data;

/**
 * 取消分阶段发布
 */
@Data
@WxApi(url = MerchantApis.Code.REVERT_GRAY_RELEASE, method = WxApi.Method.GET)
public class GrayReleaseRevertRequest extends AuthorizerBaseRequest<GrayReleaseRevertResponse> {

}
