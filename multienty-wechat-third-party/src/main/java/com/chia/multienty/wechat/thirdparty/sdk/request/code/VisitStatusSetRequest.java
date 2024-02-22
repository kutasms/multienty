package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.VisitStatusSetResponse;
import lombok.Data;

/**
 * 设置小程序服务状态
 */
@Data
@WxApi(url = MerchantApis.Code.SET_VISIT_STATUS)
public class VisitStatusSetRequest extends AuthorizerBaseRequest<VisitStatusSetResponse> {
    /**
     * 设置可访问状态，发布后默认可访问，close 为不可见，open 为可见
     */
    private String action;
}
