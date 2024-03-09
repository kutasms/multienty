package com.chia.multienty.core.strategy.sms.aliyun.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AliyunSMSOrigResponse {
    @JsonProperty("Message")
    private String message;
    @JsonProperty("RequestId")
    private String requestId;
    @JsonProperty("Code")
    private String code;
    @JsonProperty("BizId")
    private String bizId;
}
