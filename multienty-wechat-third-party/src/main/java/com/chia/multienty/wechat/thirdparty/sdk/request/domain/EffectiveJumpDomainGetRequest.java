package com.chia.multienty.wechat.thirdparty.sdk.request.domain;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.EffectiveJumpDomainGetResponse;
import lombok.Data;

/**
 * 获取发布后生效业务域名列表
 */
@Data
@WxApi(url = MerchantApis.Domain.GET_EFFECTIVE_JUMP_DOMAIN)
public class EffectiveJumpDomainGetRequest extends AuthorizerBaseRequest<EffectiveJumpDomainGetResponse> {
}
