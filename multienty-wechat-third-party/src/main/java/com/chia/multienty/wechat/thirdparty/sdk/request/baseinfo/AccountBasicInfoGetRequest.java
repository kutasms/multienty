package com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.AccountBasicInfoGetResponse;
import lombok.Data;

/**
 * 获取基本信息
 */
@Data
@WxApi(url = MerchantApis.BaseInfo.GET_BASE_INFO)
public class AccountBasicInfoGetRequest extends AuthorizerBaseRequest<AccountBasicInfoGetResponse> {

}
