package com.chia.multienty.wechat.thirdparty.sdk.request.domain;

import com.chia.multienty.wechat.thirdparty.define.ThirdPlatformApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.ComponentBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.ThirdPartyServerDomainModifyResponse;
import lombok.Data;

/**
 * 设置第三方平台服务器域名
 */
@Data
@WxApi(url = ThirdPlatformApis.Domain.MODIFY_THIRD_PARTY_SERVER_DOMAIN)
public class ThirdPartyServerDomainModifyRequest extends ComponentBaseRequest<ThirdPartyServerDomainModifyResponse> {
    /**
     * 操作类型。可选值请看下文
     */
    private String action;
    /**
     * 最多可以添加1000个服务器域名，以;隔开。注意：域名不需带有http:// 等协议内容，也不能在域名末尾附加详细的 URI 地址，严格按照类似 www.qq.com 的写法。
     */
    private String wxaServerDomain;
    /**
     * 是否同时修改“全网发布版本的值”。（false：只改“测试版”；true：同时改“测试版”和“全网发布版”）省略时，默认为false
     */
    private Boolean isModifyPublishedTogether;
}
