package com.chia.multienty.wechat.thirdparty.sdk.request.domain;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.JumpDomainModifyDirectlyResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 快速配置小程序业务域名
 */
@Data
@WxApi(url = MerchantApis.Domain.MODIFY_JUMP_DOMAIN_DIRECTLY)
public class JumpDomainModifyDirectlyRequest extends AuthorizerBaseRequest<JumpDomainModifyDirectlyResponse> {
    /**
     * 操作类型
     */
    private String action;
    /**
     * 小程序业务域名，当 action 参数是 get 时不需要此字段
     */
    @JsonProperty(value = "webviewdomain")
    private List<String> webViewDomain;
}
