package com.chia.multienty.core.strategy.sms.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SMSType {
    ALIYUN("aliyun", "阿里云");

    private String alias;
    private String description;
}
