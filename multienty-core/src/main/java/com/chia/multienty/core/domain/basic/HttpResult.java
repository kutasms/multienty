package com.chia.multienty.core.domain.basic;

import lombok.Data;

/**
 * 物流请求接口返回结果实体
 */
@Data
public class HttpResult {
    private int status;
    private String body;
    private String error;

    public HttpResult() {
    }

    public HttpResult(int status, String body, String error) {
        this.status = status;
        this.body = body;
        this.error = error;
    }
}
