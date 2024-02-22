package com.chia.multienty.wechat.thirdparty.sdk.response.wxa;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

/**
 * 小程序认证响应数据
 */
@Data
public class WxAuthResponse extends BaseResponse {
    /**
     * 认证任务id
     */
    private String taskId;
    /**
     * 小程序管理员授权链接
     */
    private String authUrl;
}
