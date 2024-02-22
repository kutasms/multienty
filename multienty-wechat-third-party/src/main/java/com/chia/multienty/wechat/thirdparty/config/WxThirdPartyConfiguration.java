package com.chia.multienty.wechat.thirdparty.config;


import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.wechat.thirdparty.sdk.tools.*;
import com.chia.multienty.wechat.thirdparty.sdk.tools.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.multi-tenant.wechat.third-party", name = "enabled", havingValue = "true")
public class WxThirdPartyConfiguration {
    private final YamlMultiTenantProperties properties;

    private final StringRedisService stringRedisService;

    @Bean
    public WxTPApiExecutor wxApiExecutor() {
        return SpringUtil.getBeanOrCreate(WxTPApiExecutor.class);
    }

    @Bean
    public WxTPAuthTool wxTPAuthTool() {
        return SpringUtil.getBeanOrCreate(WxTPAuthTool.class);
    }

    @Bean
    public WxTPReceiver wxTPReceiver() {
        return SpringUtil.getBeanOrCreate(WxTPReceiver.class);
    }

    @Bean
    public WxTPTokenProvider wxTPTokenProvider() {
        return SpringUtil.getBeanOrCreate(WxTPTokenProvider.class);
    }

    public WxTPCodeTool wxTPCodeTool() {
        return SpringUtil.getBeanOrCreate(WxTPCodeTool.class);
    }
}
