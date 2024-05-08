package com.chia.multienty.wechat.thirdparty.define.code;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class ExtObj {
    @JsonSerialize(as = String.class)
    private String programId;
    @JsonSerialize(as = String.class)
    private String tenantId;
    private String appId;
    private String appName;
}
