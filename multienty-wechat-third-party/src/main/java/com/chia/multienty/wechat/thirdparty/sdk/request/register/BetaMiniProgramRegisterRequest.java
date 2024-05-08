package com.chia.multienty.wechat.thirdparty.sdk.request.register;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.register.BetaMiniProgramRegisterResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 注册试用小程序
 */
@Data
@WxApi(url = MerchantApis.Register.REG_BETA_MINI_PROGRAM)
public class BetaMiniProgramRegisterRequest extends ComponentBaseRequest<BetaMiniProgramRegisterResponse> {
    /**
     * 小程序名称，昵称半自动设定，强制后缀“的体验小程序”。
     * 且该参数会进行关键字检查，如果命中品牌关键字则会报错。
     * 如遇到品牌大客户要用试用小程序，建议用户先换个名字，
     * 认证后再修改成品牌名。 只支持4-30个字符
     */
    @JsonProperty("name")
    private String name;
    /**
     * 微信用户的openid（不是微信号），试用小程序创建成功后会默认将该用户设置为小程序管理员。
     */
    @JsonProperty("openid")
    private String openid;
}
