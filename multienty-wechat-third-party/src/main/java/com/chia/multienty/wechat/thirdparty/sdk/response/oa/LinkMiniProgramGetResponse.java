package com.chia.multienty.wechat.thirdparty.sdk.response.oa;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.oa.LinkedMpp;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取公众号关联的小程序响应数据
 */
public class LinkMiniProgramGetResponse extends BaseResponse {
    /**
     * 关联的小程序列表
     */
    @JsonProperty(value = "wxopens")
    private LinkedMpp wxOpens;
}
