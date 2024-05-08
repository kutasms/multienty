package com.chia.multienty.wechat.scheduling.rabbitmq.domain.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AuthorizerAccessTokenRefreshBO implements Serializable {
    private Long tenantId;
    private String appId;
    private String refreshToken;
}
