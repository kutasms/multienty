package com.chia.multienty.wechat.thirdparty.sdk.request.register;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.MiniProgramRegisterByOfficialAccountResponse;
import lombok.Data;

/**
 * 复用公众号主体快速注册小程序请求
 */
@Data
@WxApi(url = MerchantApis.Register.REG_MINI_PROGRAM_BY_OFFICIAL_ACCOUNT)
public class MiniProgramRegisterByOfficialAccountRequest
        extends AuthorizerBaseRequest<MiniProgramRegisterByOfficialAccountResponse> {
    /**
     * 公众号扫码授权的凭证(公众平台扫码页面回跳到第三方平台时携带)，要看复用公众号主体快速注册小程序使用说明
     * 返回参数
     */
    private String ticket;
}
