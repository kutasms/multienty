package com.chia.multienty.wechat.thirdparty.sdk.request.domain;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.ThirdPartyJumpDomainConfirmFileGetResponse;
import lombok.Data;

/**
 * 获取第三方平台业务域名校验文件
 */
@Data
@WxApi(url = ThirdPlatformApis.Domain.GET_THIRD_PARTY_JUMP_DOMAIN_CONFIRM_FILE)
public class ThirdPartyJumpDomainConfirmFileGetRequest extends ComponentBaseRequest<ThirdPartyJumpDomainConfirmFileGetResponse> {

}
