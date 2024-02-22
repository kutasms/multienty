package com.chia.multienty.core.strategy.pay.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MTPrepayResponse {
    @SerializedName("appId")
    @JsonProperty("appId")
    private String appId;
    @SerializedName("timeStamp")
    @JsonProperty("timestamp")
    private String timestamp;
    @SerializedName("nonceStr")
    @JsonProperty("nonceStr")
    private String nonceStr;
    @SerializedName("package")
    @JsonProperty("packageVal")
    private String packageVal;
    @SerializedName("signType")
    @JsonProperty("signType")
    private String signType;
    @SerializedName("paySign")
    @JsonProperty("paySign")
    private String paySign;
}
