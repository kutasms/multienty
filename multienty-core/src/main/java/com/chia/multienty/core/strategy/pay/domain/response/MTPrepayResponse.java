package com.chia.multienty.core.strategy.pay.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MTPrepayResponse {
    @JsonProperty("appId")
    private String appId;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("nonceStr")
    private String nonceStr;
    @JsonProperty("packageVal")
    private String packageVal;
    @JsonProperty("signType")
    private String signType;
    @JsonProperty("paySign")
    private String paySign;
}
