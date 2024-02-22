package com.chia.multienty.wechat.thirdparty.sdk.response.wxa;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import lombok.Data;

/**
 * 小程序认证重新提审响应数据
 */
@Data
public class WxReAuthResponse extends BaseResponse {
    /**
     * 认证任务id
     */
    private String taskId;
    /**
     * 小程序管理员授权链接
     */
    private String authUrl;
}
