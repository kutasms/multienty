package com.chia.multienty.wechat.thirdparty.sdk.request.domain;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.ThirdPartyJumpDomainModifyResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 设置第三方平台业务域名
 */
@Data
@WxApi(url = ThirdPlatformApis.Domain.MODIFY_THIRD_PARTY_JUMP_DOMAIN)
public class ThirdPartyJumpDomainModifyRequest extends ComponentBaseRequest<ThirdPartyJumpDomainModifyResponse> {
    /**
     * 操作类型。可选值请看下文
     */
    @JsonProperty("action")
    private String action;
    /**
     * 最多可以添加200个小程序业务域名，以;隔开。注意：域名不需带有http:// 等协议内容，也不能在域名末尾附加详细的 URI 地址，严格按照类似 www.qq.com 的写法
     */
    @JsonProperty(value = "wxa_jump_h5_domain")
    private String wxaJumpH5Domain;
    /**
     * 是否同时修改“全网发布版本的值”。（false：只改“测试版”；true：同时改“测试版”和“全网发布版”）省略时，默认为false
     */
    @JsonProperty("is_modify_published_together")
    private Boolean isModifyPublishedTogether;
}
