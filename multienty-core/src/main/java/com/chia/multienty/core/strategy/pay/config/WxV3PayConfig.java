package com.chia.multienty.core.strategy.pay.config;

import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.service.partnerpayments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.refund.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "spring.multienty.wechat.pay",name="sp-cert-load-enabled", havingValue = "true")
public class WxV3PayConfig {
    private final YamlMultientyProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public RSAAutoCertificateConfig wechatV3Config() {
        return
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(properties.getWechat().getPay().getSpMchId())
                        .privateKeyFromPath(properties.getWechat().getPay().getSpPrivateKeyPath())
                        .merchantSerialNumber(properties.getWechat().getPay().getSpSerialNumber())
                        .apiV3Key(properties.getWechat().getPay().getSpApiV3Key())
                        .build();
    }

    @Bean
    public NotificationConfig notificationConfig() {
        return wechatV3Config();
    }

    @Bean
    @ConditionalOnMissingBean
    public JsapiServiceExtension jsapiService() {
        return new JsapiServiceExtension.Builder().config(wechatV3Config()).build();
    }
    @Bean
    @ConditionalOnMissingBean
    public RefundService refundService() {
        return new RefundService.Builder().config(wechatV3Config()).build();
    }
}
