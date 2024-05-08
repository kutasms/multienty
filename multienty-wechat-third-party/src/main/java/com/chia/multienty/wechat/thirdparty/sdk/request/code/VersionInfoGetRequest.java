package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.VersionInfoGetResponse;
import lombok.Data;

/**
 * 查询小程序版本信息
 */
@Data
@WxApi(url = MerchantApis.Code.GET_VERSION_INFO)
public class VersionInfoGetRequest extends AuthorizerBaseRequest<VersionInfoGetResponse> {

}
