package com.chia.multienty.wechat.thirdparty.define.platform;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 小程序配置的合法域名信息
 */
@Data
public class MppNetwork {

    /**
     * request合法域名
     */
    @JsonProperty(value = "RequestDomain")
    private List<String> requestDomain;

    /**
     * 	socket合法域名
     */
    @JsonProperty(value = "WsRequestDomain")
    private List<String> wsRequestDomain;

    /**
     * uploadFile合法域名
     */
    @JsonProperty(value = "UploadDomain")
    private List<String> uploadDomain;

    /**
     * 	downloadFile合法域名
     */
    @JsonProperty(value = "DownloadDomain")
    private List<String> downloadDomain;

    /**
     * udp合法域名
     */
    @JsonProperty(value = "UDPDomain")
    private List<String> udpDomain;

    /**
     * tcp合法域名
     */
    @JsonProperty(value = "TCPDomain")
    private List<String> tcpDomain;
}
