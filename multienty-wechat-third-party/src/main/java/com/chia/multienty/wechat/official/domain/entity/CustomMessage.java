package com.chia.multienty.wechat.official.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class CustomMessage {
    @JsonProperty("touser")
    private String toUser;
    @JsonProperty("msgtype")
    private String msgType = "text";
    @JsonProperty("text")
    private Map<String, Object> text;
}
