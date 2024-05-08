package com.chia.multienty.wechat.thirdparty.sdk.request.register;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.BetaMiniProgramNickNameModifyResponse;
import lombok.Data;

/**
 * 修改试用小程序名称
 */
@Data
@WxApi(url = MerchantApis.Register.MODIFY_BETA_MINI_PROGRAM_NICK_NAME)
public class BetaMiniProgramNickNameModifyRequest extends AuthorizerBaseRequest<BetaMiniProgramNickNameModifyResponse> {
    /**
     * 小程序名称，昵称半自动设定，强制后缀“的体验小程序”。
     * 且该参数会进行关键字检查，如果命中品牌关键字则会报错。
     * 如遇到品牌大客户要用试用小程序，建议用户先换个名字，认证后再修改成品牌名
     */
    private String name;
}
