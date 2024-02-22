package com.chia.multienty.core.domain.wechat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "WxDataWrapper",description = "微信数据包装")
public class WxDataWrapper {

    @JSONField(name="openid")
    @JsonProperty("openid")
    private String openId;



    @JSONField(name="session_key")
    @JsonProperty("session_key")
    private String sessionKey;

    @JSONField(name="access_token")
    @JsonProperty("access_token")
    private String accessToken;

    @JSONField(name="phoneNumber")
    @JsonProperty("phoneNumber")
    private String phoneNumber;
}
