package com.chia.multienty.core.properties.yaml;

import lombok.Data;

import java.util.Map;

@Data
public class YamlMultientyWechatPayProperties {
    private String notifyUrlPrefix;
    private String v2H5RedirectUrl;
    private Map<String, String> v2NotifyUrls;
    private Map<String, String> v3NotifyUrls;
    private Map<String, String> v3PartnerNotifyUrls;
    /**
     * 服务商mchid
     */
    private String spMchId;
    /**
     * 服务商证书路径
     */
    private String spCertPath;
    /**
     * 服务商私钥路径
     */
    private String spPrivateKeyPath;
    /**
     * 服务商证书序列号
     */
    private String spSerialNumber;
    /**
     * 服务商apiv3密钥
     */
    private String spApiV3Key;

    /**
     * 当前实现
     */
    private String active = "wechat-v3";
}
