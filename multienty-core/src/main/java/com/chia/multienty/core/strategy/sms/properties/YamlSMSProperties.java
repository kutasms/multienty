package com.chia.multienty.core.strategy.sms.properties;

import com.chia.multienty.core.domain.enums.SMSProviderType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Data
@ConfigurationProperties(prefix = "spring.sms", ignoreUnknownFields = true)
@Component
public class YamlSMSProperties {
    private String runningMode;
    private Boolean enabled;
    private String currentProvider;
    private AliyunSMSProperties aliyun;
    public SMSProviderType getProvider() {
        if(!StringUtils.hasText(currentProvider)) {
            return null;
        }
        return SMSProviderType.valueOf(currentProvider);
    }
}
