package com.chia.multienty.wechat.thirdparty.sdk.request.code;

import com.chia.multienty.wechat.thirdparty.annotation.WxUrlEncoding;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.TrialQRCodeGetResponse;
import lombok.Data;

/**
 * 获取体验版二维码
 */
@Data
@WxApi(url = MerchantApis.Code.GET_TRIAL_QRCODE, method = WxApi.Method.GET)
public class TrialQRCodeGetRequest extends AuthorizerBaseRequest<TrialQRCodeGetResponse> {
    /**
     * 指定二维码扫码后直接进入指定页面并可同时带上参数
     */
    @WxUrlEncoding
    private String path;
}
