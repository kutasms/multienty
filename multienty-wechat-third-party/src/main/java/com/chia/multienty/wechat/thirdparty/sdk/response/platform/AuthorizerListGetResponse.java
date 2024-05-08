package com.chia.multienty.wechat.thirdparty.sdk.response.platform;

import com.chia.multienty.wechat.thirdparty.define.platform.SimpleAuthorizationInfo;
import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 拉取已授权的账号信息响应数据
 */
@Data
public class AuthorizerListGetResponse extends BaseResponse {
    @JsonProperty("total_count")
    private Integer totalCount;
    @JsonProperty("list")

    private List<SimpleAuthorizationInfo> list;
}
