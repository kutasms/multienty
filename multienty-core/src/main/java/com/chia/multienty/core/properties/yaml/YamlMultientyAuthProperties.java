package com.chia.multienty.core.properties.yaml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.multienty.security.auth")
public class YamlMultientyAuthProperties implements YamlProperties {
    private String header = "X-TOKEN";
    private String tokenPrefix;
    private String base64Secret;
    /**
     * 管理端持有令牌时间
     */
    private Integer accessTokenExpired = 86400;
    /**
     * 刷新令牌有效期
     */
    private Integer refreshTokenExpired = 86400;
    /**
     * 续约时长
     */
    private Integer renewDuration;

    private Boolean multipointLoginEnabled;



}
