package com.chia.multienty.wechat.thirdparty.sdk.request.baseinfo;

import com.chia.multienty.wechat.thirdparty.define.MerchantApis;
import com.chia.multienty.wechat.thirdparty.annotation.WxApi;
import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.baseinfo.NickNameStatusGetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 查询小程序名称审核状态
 */
@Data
@WxApi(url = MerchantApis.BaseInfo.GET_NICK_NAME_STATUS)
public class NickNameStatusGetRequest extends AuthorizerBaseRequest<NickNameStatusGetResponse> {
    /**
     * 审核单 id，由设置名称接口返回
     */
    @JsonProperty("audit_id")
    private Long auditId;
}
