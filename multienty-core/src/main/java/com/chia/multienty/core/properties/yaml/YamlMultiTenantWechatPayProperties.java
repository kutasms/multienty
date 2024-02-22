package com.chia.multienty.core.properties.yaml;

import lombok.Data;

import java.util.Map;

@Data
public class YamlMultiTenantWechatPayProperties {
    private String notifyUrlPrefix;
    private String v2H5RedirectUrl;
    private Map<String, String> v2NotifyUrls;
    private Map<String, String> v3NotifyUrls;

    private String active = "wechat-v3";
}
