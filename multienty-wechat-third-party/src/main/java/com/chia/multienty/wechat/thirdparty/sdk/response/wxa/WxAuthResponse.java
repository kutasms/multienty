package com.chia.multienty.wechat.thirdparty.sdk.response.wxa;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 小程序认证响应数据
 */
@Data
public class WxAuthResponse extends BaseResponse {
    /**
     * 认证任务id
     */
    @JsonProperty("taskid")
    private String taskId;
    /**
     * 小程序管理员授权链接
     */
    @JsonProperty("auth_url")
    private String authUrl;
}
