package com.chia.multienty.core.domain.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 小程序登录响应
 */
@Data
public class WxLoginResponseBO implements Serializable {
    /**
     * 会话密钥
     */
    @JsonProperty(value = "session_key")
    private String sessionKey;
    /**
     * 用户唯一标识的 openid
     */
    @JsonProperty(value = "openid")
    private String openId;
    /**
     * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回
     */
    @JsonProperty(value = "unionid")
    private String unionId;
}
