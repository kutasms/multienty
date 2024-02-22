package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.JumpQRCodePublishResponse;
import lombok.Data;

/**
 * 发布已设置的二维码规则
 */
@Data
@WxApi(url = MerchantApis.OfficialAccount.PUBLISH_JUMP_QRCODE)
public class JumpQRCodePublishRequest extends AuthorizerBaseRequest<JumpQRCodePublishResponse> {
    /**
     * 二维码规则。如果是服务号，则是服务号的带参的二维码url
     */
    private String prefix;
}
