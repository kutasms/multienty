package com.chia.multienty.core.properties.yaml;

import lombok.Data;

@Data
public class YamlMultientyWechatOfficialAccountProperties {
    private String origId;
    private String appId;
    private String appSecret;
    private String encodingAESKey;
    private String token;
}
