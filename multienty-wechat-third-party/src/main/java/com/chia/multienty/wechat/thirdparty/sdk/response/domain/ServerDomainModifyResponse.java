package com.chia.multienty.wechat.thirdparty.sdk.response.domain;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 配置小程序服务器域名
 */
@Data
public class ServerDomainModifyResponse extends BaseResponse {
    /**
     * request 合法域名
     */
    @JsonProperty(value = "requestdomain")
    private List<String> requestDomain;
    /**
     * socket 合法域名
     */
    @JsonProperty(value = "wsrequestdomain")
    private List<String> wsRequestDomain;
    /**
     * uploadFile 合法域名
     */
    @JsonProperty(value = "uploaddomain")
    private List<String> uploadDomain;
    /**
     * downloadFile 合法域名
     */
    @JsonProperty(value = "downloaddomain")
    private List<String> downloadDomain;
    /**
     * udp 合法域名
     */
    @JsonProperty(value = "udpdomain")
    private List<String> udpDomain;
    /**
     * tcp 合法域名
     */
    @JsonProperty(value = "tcpdomain")
    private List<String> tcpDomain;

    /**
     * request 合法域名
     */
    @JsonProperty(value = "invalid_requestdomain")
    private List<String> invalidRequestDomain;
    /**
     * socket 合法域名
     */
    @JsonProperty(value = "invalid_wsrequestdomain")
    private List<String> invalidWsRequestDomain;
    /**
     * uploadFile 合法域名
     */
    @JsonProperty(value = "invalid_uploaddomain")
    private List<String> invalidUploadDomain;
    /**
     * downloadFile 合法域名
     */
    @JsonProperty(value = "invalid_downloaddomain")
    private List<String> invalidDownloadDomain;
    /**
     * udp 合法域名
     */
    @JsonProperty(value = "invalid_udpdomain")
    private List<String> invalidUdpDomain;
    /**
     * tcp 合法域名
     */
    @JsonProperty(value = "invalid_tcpdomain")
    private List<String> invalidTcpDomain;
    /**
     * 没有经过icp备案的域名
     */
    @JsonProperty(value = "no_icp_domain")
    private List<String> noIcpDomain;
}
