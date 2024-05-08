package com.chia.multienty.wechat.thirdparty.config;


import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.wechat.thirdparty.sdk.tools.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.multienty.wechat.third-party", name = "enabled", havingValue = "true")
public class WxThirdPartyConfiguration {
    private final YamlMultientyProperties properties;

    private final StringRedisService stringRedisService;

    @Bean
    public WxTPApiExecutor wxApiExecutor() {
        return new WxTPApiExecutor();
    }

    @Bean
    public WxTPAuthTool wxTPAuthTool() {
        return new WxTPAuthTool();
    }

    @Bean
    public WxTPReceiver wxTPReceiver() {
        return new WxTPReceiver();
    }

    @Bean
    public WxTPTokenProvider wxTPTokenProvider() {
        return new WxTPTokenProvider();
    }

    @Bean
    public WxTPCodeTool wxTPCodeTool() {
        return new WxTPCodeTool();
    }
}
