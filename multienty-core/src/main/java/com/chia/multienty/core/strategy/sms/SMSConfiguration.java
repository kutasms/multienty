package com.chia.multienty.core.strategy.sms;

import com.chia.multienty.core.strategy.sms.aliyun.AliyunService;
import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.core.strategy.sms.properties.SMSProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "spring.sms", name = "enabled", havingValue = "true")
public class SMSConfiguration {
    private SMSProperties properties;

    @Bean
    public SMSService smsService() {
        SMSService service = null;
        switch (properties.getActive()) {
            case "aliyun":
                service = SpringUtil.getBeanOrCreate(AliyunService.class);
                break;
        }
        return service;
    }
}
