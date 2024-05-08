package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.JumpQRCodeDeleteResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 删除已设置的二维码规则
 */
@Data
@WxApi(url = MerchantApis.OfficialAccount.DELETE_JUMP_QRCODE)
public class JumpQRCodeDeleteRequest extends AuthorizerBaseRequest<JumpQRCodeDeleteResponse> {
    /**
     * 二维码规则
     */
    @JsonProperty("prefix")
    private String prefix;
    /**
     * 小程序appid。
     * 删除“扫服务号二维码打开小程序”的二维码规则才需要传这个参数
     */
    @JsonProperty(value = "appid")
    private String appId;
}
