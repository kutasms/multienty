package com.chia.multienty.wechat.thirdparty.sdk.request.domain;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.JumpDomainConfirmFileGetResponse;
import lombok.Data;

/**
 * 获取业务域名校验文件
 */
@Data
@WxApi(url = MerchantApis.Domain.GET_JUMP_DOMAIN_CONFIRM_FILE)
public class JumpDomainConfirmFileGetRequest extends AuthorizerBaseRequest<JumpDomainConfirmFileGetResponse> {
}
