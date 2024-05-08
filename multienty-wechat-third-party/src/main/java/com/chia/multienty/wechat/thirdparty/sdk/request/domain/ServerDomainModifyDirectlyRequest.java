package com.chia.multienty.wechat.thirdparty.sdk.request.domain;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.domain.ServerDomainModifyDirectlyResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 快速配置小程序服务器域名
 */
@Data
@WxApi(url = MerchantApis.Domain.MODIFY_SERVER_DOMAIN_DIRECTLY)
public class ServerDomainModifyDirectlyRequest extends AuthorizerBaseRequest<ServerDomainModifyDirectlyResponse> {
    /**
     * 操作类型
     */
    @JsonProperty(value = "action")
    private String action;
    /**
     * request 合法域名；当 action 是 get 时不需要此字段
     */
    @JsonProperty(value = "requestdomain")
    private List<String> requestDomain;
    /**
     * socket 合法域名；当 action 是 get 时不需要此字段
     */
    @JsonProperty(value = "wsrequestdomain")
    private List<String> wsRequestDomain;
    /**
     * uploadFile 合法域名；当 action 是 get 时不需要此字段
     */
    @JsonProperty(value = "uploaddomain")
    private List<String> uploadDomain;
    /**
     * downloadFile 合法域名；当 action 是 get 时不需要此字段
     */
    @JsonProperty(value = "downloaddomain")
    private List<String> downloadDomain;
    /**
     * udp 合法域名；当 action 是 get 时不需要此字段
     */
    @JsonProperty(value = "udpdomain")
    private List<String> udpDomain;
    /**
     * tcp 合法域名；当 action 是 get 时不需要此字段
     */
    @JsonProperty(value = "tcpdomain")
    private List<String> tcpDomain;
}
