package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.MiniProgramLinkResponse;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 公众号关联小程序
 */
@Data
@WxApi(url = MerchantApis.OfficialAccount.LINK_MINI_PROGRAM)
public class MiniProgramLinkRequest extends AuthorizerBaseRequest<MiniProgramLinkResponse> {
    /**
     * 小程序 appid
     */
    @JsonProperty(value = "appid")
    private String appId;
    /**
     * 是否发送模板消息通知公众号粉丝。1表示是，0表示否
     */
    private Integer notifyUsers;
    /**
     * 是否展示公众号主页中。1表示是，0表示否
     */
    private Integer showProfile;
}
