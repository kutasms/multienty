package com.chia.multienty.wechat.thirdparty.parameter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ServerDomainModifyDirectlyParameter {
    /**
     * 操作类型
     */
    private String action;
    /**
     * request 合法域名；当 action 是 get 时不需要此字段
     */
    private List<String> requestDomains;
    /**
     * socket 合法域名；当 action 是 get 时不需要此字段
     */
    private List<String> wsRequestDomains;
    /**
     * uploadFile 合法域名；当 action 是 get 时不需要此字段
     */
    private List<String> uploadDomains;
    /**
     * downloadFile 合法域名；当 action 是 get 时不需要此字段
     */
    private List<String> downloadDomains;
    /**
     * udp 合法域名；当 action 是 get 时不需要此字段
     */
    private List<String> udpDomains;
    /**
     * tcp 合法域名；当 action 是 get 时不需要此字段
     */
    private List<String> tcpDomains;
}
