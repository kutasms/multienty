package com.chia.multienty.core.properties.yaml;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.multienty")
@Getter
@Setter
public class YamlMultientyProperties {
    /**
     * 是否演示系统
     */
    private Boolean isDemo;
    /**
     * 是否启动独立数据库模式
     * 普通客户为分表的租户模式，有部分客户可能需要部署独立数据库
     */
    private YamlMultientyAlgorithmProperties algorithms;

    /**
     * 是否启用基础模块，启用后将提供基础模块controller服务
     */
    private Boolean baseModuleEnabled = false;

    /**
     * 是否启用用户模块，启用后将提供用户模块controller服务
     */
    private Boolean userModuleEnabled = false;
    /**
     * 登录失败阈值
     */
    private Integer loginFailureThreshold;

    /**
     * 独立部署租户数据库名称前缀
     */
    private String standaloneTenantDbNamePrefix = "db-sa-";
    /**
     * 主控数据库名称
     */
    private String masterDbName = "master";

    /**
     * jackson相关配置
     */
    private YamlMultientyJacksonProperties jackson;

    /**
     * 文件相关配置
     */
    private YamlMultientyFileProperties file;

    /**
     * 域名
     */
    private String domain;
    private String Sm4PrivateKey;
    /**
     * 安全配置
     */
    private YamlMultientySecurityProperties security;
    /**
     * 微信配置
     */
    private YamlMultientyWechatProperties wechat;
    /**
     * 调度任务配置
     */
    private YamlMultientySchedulingProperties scheduling;
}
