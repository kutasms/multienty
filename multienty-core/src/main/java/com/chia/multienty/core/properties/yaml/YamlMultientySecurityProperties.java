package com.chia.multienty.core.properties.yaml;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

@Data
@ConfigurationProperties(prefix = "spring.multienty.security")
public class YamlMultientySecurityProperties {
    private YamlMultientyRsaProperties rsa;
    private YamlMultientyAuthProperties auth;

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
