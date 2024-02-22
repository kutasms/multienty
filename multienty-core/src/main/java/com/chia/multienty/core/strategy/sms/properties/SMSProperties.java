package com.chia.multienty.core.strategy.sms.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "spring.sms", ignoreUnknownFields = true)
@Component
public class SMSProperties {
    private String active;
    private AliyunSMSProperties aliyun;
}
