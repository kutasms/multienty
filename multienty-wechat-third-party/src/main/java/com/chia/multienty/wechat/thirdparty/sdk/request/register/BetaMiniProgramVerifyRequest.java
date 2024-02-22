package com.chia.multienty.wechat.thirdparty.sdk.request.register;

import com.chia.multienty.wechat.thirdparty.define.register.EnterpriseVerifyInfo;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.BetaMiniProgramVerifyResponse;
import lombok.Data;

/**
 * 试用小程序快速认证
 */
@Data
@WxApi(url = MerchantApis.Register.VERIFY_BETA_MINI_PROGRAM)
public class BetaMiniProgramVerifyRequest extends AuthorizerBaseRequest<BetaMiniProgramVerifyResponse> {
    /**
     * 企业法人认证需要的信息
     */
    private EnterpriseVerifyInfo verifyInfo;
}
