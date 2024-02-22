package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.chia.multienty.wechat.thirdparty.sdk.request.AuthorizerBaseRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.oa.ShowItemSetResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 设置扫码关注的公众号
 */
@Data
public class ShowItemSetRequest extends AuthorizerBaseRequest<ShowItemSetResponse> {
    /**
     * 是否打开扫码关注组件，0 关闭，1 开启
     */
    private Integer wxaSubscribeBizFlag;
    /**
     * 如果开启，需要传新的公众号 appid
     */
    @JsonProperty("appid")
    private String appId;
}
