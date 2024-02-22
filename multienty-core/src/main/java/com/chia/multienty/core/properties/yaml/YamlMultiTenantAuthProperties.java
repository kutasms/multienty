package com.chia.multienty.core.properties.yaml;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

@Data
@ConfigurationProperties(prefix = "spring.kuta.multi-tenant.security.auth")
public class YamlMultiTenantAuthProperties implements YamlProperties {
    private String header;
    private String tokenPrefix;
    private String base64Secret;
    /**
     * 管理端持有令牌时间
     */
    private Integer tokenHoldingTime = 86400;
    /**
     * 客户持有令牌时间
     */
    private Integer customerTokenTime = 86400;
    private Integer renewTime;
    private Boolean multipointLoginEnabled;
    private String[] ignorePaths;

    /**
     * 判断当前路径是否需要认证
     * @param path 路径
     */
    public boolean needAuthorizing(String path) {

        boolean needAuth = true;
        PathMatcher matcher = new AntPathMatcher();
        if(ObjectUtil.isNotEmpty(this.getIgnorePaths())){
            for (String ignorePath : this.getIgnorePaths()) {
                if (matcher.match(ignorePath, path)) {
                    needAuth = false;
                }
            }
        }
        return needAuth;
    }
}
