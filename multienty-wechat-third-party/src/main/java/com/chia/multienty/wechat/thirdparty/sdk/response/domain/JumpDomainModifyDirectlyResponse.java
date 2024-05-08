package com.chia.multienty.wechat.thirdparty.sdk.response.domain;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 快速配置小程序业务域名响应数据
 */
@Data
public class JumpDomainModifyDirectlyResponse extends BaseResponse {
    /**
     * 业务域名
     */
    @JsonProperty(value = "webviewdomain")
    private List<String> webViewDomain;
}
