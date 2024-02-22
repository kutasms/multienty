package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.LinkMiniProgramGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import lombok.Data;

/**
 * 获取公众号关联的小程序
 */
@Data
@WxApi(url = MerchantApis.OfficialAccount.GET_LINK_MINI_PROGRAM)
public class LinkMiniProgramGetRequest extends AuthorizerBaseRequest<LinkMiniProgramGetResponse> {

}
