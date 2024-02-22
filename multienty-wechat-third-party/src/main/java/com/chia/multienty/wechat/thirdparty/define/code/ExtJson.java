package com.chia.multienty.wechat.thirdparty.define.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ExtJson {
    private Boolean extEnable;
    @JsonProperty(value = "evtAppid")
    private String extAppId;
    private Boolean directCommit = false;
    private Object ext;
    private List<String> requiredPrivateInfos;
}
