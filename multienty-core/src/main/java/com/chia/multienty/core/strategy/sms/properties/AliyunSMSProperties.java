package com.chia.multienty.core.strategy.sms.properties;

import lombok.Data;

import java.util.Map;

@Data
public class AliyunSMSProperties {
    private String regionId;
    private String name;
    private String domain;
    private String keyId;
    private String keySecret;
    private String signName;
    private String readTimeout;
    private String connectTimeout;
    private Map<String, String> templates;

}
