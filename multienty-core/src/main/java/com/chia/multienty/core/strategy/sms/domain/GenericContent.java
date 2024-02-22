package com.chia.multienty.core.strategy.sms.domain;

import lombok.Data;

import java.util.Map;

@Data
public class GenericContent extends SMSEntityBO{
    private String templateKey;
    private Map<String, String> parameters;

}
