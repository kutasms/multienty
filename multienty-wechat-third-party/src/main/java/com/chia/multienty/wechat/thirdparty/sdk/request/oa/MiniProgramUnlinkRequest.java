package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.MiniProgramUnlinkResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 公众号解除关联小程序
 */
@Data
@WxApi(url = MerchantApis.OfficialAccount.UNLINK_MINI_PROGRAM)
public class MiniProgramUnlinkRequest extends AuthorizerBaseRequest<MiniProgramUnlinkResponse> {
    /**
     * 小程序 appid
     */
    @JsonProperty(value = "appid")
    private String appId;
}
