package com.chia.multienty.wechat.thirdparty.sdk.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AuthorizerBaseRequest<R> implements IWxRequest<R> {
    /**
     * 接口调用凭证，该参数为 URL 参数，非 Body 参数。使用authorizer_access_token
     */
    @JsonIgnore
    private String accessToken;
}
