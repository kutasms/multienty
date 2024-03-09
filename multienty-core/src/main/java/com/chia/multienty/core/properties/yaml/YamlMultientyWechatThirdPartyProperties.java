package com.chia.multienty.core.properties.yaml;

import lombok.Data;

import java.util.List;

@Data
public class YamlMultientyWechatThirdPartyProperties {
    private String appId;

    private String appSecret;

    private Boolean enabled = false;

    /**
     * 消息效验Token
     */
    private String messageVerifyToken;

    /**
     * 消息加解密Key
     */
    private String messageDecryptKey;
    /**
     * 授权回跳URL
     */
    private String authRedirectUri;
    /**
     * 权限获取列表
     */
    private List<String> requiredPrivateInfos;
}
