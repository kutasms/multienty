package com.chia.multienty.wechat.thirdparty.sdk.response.register;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 复用公众号主体快速注册小程序
 */
@Data
public class MiniProgramRegisterByOfficialAccountResponse extends BaseResponse {
    /**
     * 新创建小程序的 appid
     */
    @JsonProperty(value = "appid")
    private String appId;
    /**
     * 新创建小程序的授权码；注：使用 appid 及 authorization_code 换取 authorizer_refresh_token 后需及时保存。
     */
    @JsonProperty(value = "authorization_code")
    private String authorizationCode;
    /**
     * 复用公众号微信认证小程序是否成功
     */
    @JsonProperty(value = "is_wx_verify_succ")
    private Boolean isWxVerifySuccess;
    /**
     * 小程序是否和公众号关联成功
     */
    @JsonProperty(value = "is_link_succ")
    private Boolean isLinkSuccess;
}
