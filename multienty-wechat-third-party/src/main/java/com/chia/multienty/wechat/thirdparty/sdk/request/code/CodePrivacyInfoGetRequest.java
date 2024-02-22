package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.CodePrivacyInfoGetResponse;
import lombok.Data;

/**
 * 获取隐私接口检测结果
 */
@Data
@WxApi(url = MerchantApis.Code.GET_CODE_PRIVACY_INFO,method = WxApi.Method.GET)
public class CodePrivacyInfoGetRequest extends AuthorizerBaseRequest<CodePrivacyInfoGetResponse> {
}
