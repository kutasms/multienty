package com.chia.multienty.wechat.thirdparty.sdk.request.domain;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.JumpDomainModifyResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 配置小程序业务域名
 */
@Data
@WxApi(url = MerchantApis.Domain.MODIFY_JUMP_DOMAIN)
public class JumpDomainModifyRequest extends AuthorizerBaseRequest<JumpDomainModifyResponse> {
    /**
     * 操作类型，如果没有指定 action，则默认将第三方平台登记的小程序业务域名全部添加到该小程序
     */
    @JsonProperty("action")
    private String action;
    /**
     * 小程序业务域名，当 action 参数是 get 时不需要此字段
     */
    @JsonProperty(value = "webviewdomain")
    private List<String> webViewDomain;
}
