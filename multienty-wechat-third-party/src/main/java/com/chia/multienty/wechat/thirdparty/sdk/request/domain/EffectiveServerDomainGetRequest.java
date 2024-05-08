package com.chia.multienty.wechat.thirdparty.sdk.request.domain;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.EffectiveServerDomainGetResponse;
import lombok.Data;

/**
 * 获取发布后生效服务器域名列表
 */
@Data
@WxApi(url = MerchantApis.Domain.GET_EFFECTIVE_SERVER_DOMAIN)
public class EffectiveServerDomainGetRequest extends AuthorizerBaseRequest<EffectiveServerDomainGetResponse> {

}
