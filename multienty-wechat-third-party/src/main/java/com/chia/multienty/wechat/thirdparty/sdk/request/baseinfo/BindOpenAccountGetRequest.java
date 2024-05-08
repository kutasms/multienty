package com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.BindOpenAccountGetResponse;
import lombok.Data;

@Data
@WxApi(url = MerchantApis.BaseInfo.QUERY_BIND_OPEN_ACCOUNT, method = WxApi.Method.GET)
public class BindOpenAccountGetRequest extends AuthorizerBaseRequest<BindOpenAccountGetResponse> {

}
