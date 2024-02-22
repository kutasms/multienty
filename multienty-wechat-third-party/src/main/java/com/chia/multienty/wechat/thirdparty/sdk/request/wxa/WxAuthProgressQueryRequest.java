package com.chia.multienty.wechat.thirdparty.sdk.request.wxa;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.wxa.WxAuthProgressQueryResponse;
import lombok.Data;

/**
 * 小程序认证进度查询
 */
@Data
@WxApi(url = MerchantApis.WxAuth.QUERY_MINI_PROGRAM_PROGRESS)
public class WxAuthProgressQueryRequest extends AuthorizerBaseRequest<WxAuthProgressQueryResponse> {
    /**
     * 认证任务id
     */
    private String taskId;
}
