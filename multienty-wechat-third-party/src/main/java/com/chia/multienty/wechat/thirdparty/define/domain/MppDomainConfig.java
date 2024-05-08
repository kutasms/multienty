package com.chia.multienty.wechat.thirdparty.define.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 小程序域名配置
 */
@Data
public class MppDomainConfig {
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
}
