package com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.NickNameCheckResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 小程序名称检测
 */
@Data
@WxApi(url = MerchantApis.BaseInfo.CHECK_NICK_NAME)
public class NickNameCheckRequest extends AuthorizerBaseRequest<NickNameCheckResponse> {
    /**
     * 名称（昵称）
     */
    @JsonProperty("nick_name")
    private String nickName;
}
