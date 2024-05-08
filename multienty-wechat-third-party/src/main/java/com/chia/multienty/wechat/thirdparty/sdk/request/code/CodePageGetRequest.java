package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.CodePageGetResponse;
import lombok.Data;

/**
 * 获取已上传的代码页面列表
 */
@Data
@WxApi(url = MerchantApis.Code.GET_CODE_PAGE, method = WxApi.Method.GET)
public class CodePageGetRequest extends AuthorizerBaseRequest<CodePageGetResponse> {
}
