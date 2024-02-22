package com.chia.multienty.wechat.thirdparty.sdk.response.domain;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 设置第三方平台服务器域名响应数据
 */
@Data
public class ThirdPartyServerDomainModifyResponse extends BaseResponse {
    /**
     * 目前生效的 “全网发布版”第三方平台“小程序服务器域名”。如果修改失败，该字段不会返回。如果没有已发布的第三方平台，该字段也不会返回。
     */
    @JsonProperty(value = "published_wxa_server_domain")
    private String publishedWXAServerDomain;

    /**
     * 目前生效的 “测试版”第三方平台“小程序服务器域名”。如果修改失败，该字段不会返回
     */
    @JsonProperty(value = "testing_wxa_server_domain")
    private String testingWXAServerDomain;

    /**
     * 未通过验证的域名。如果不存在未通过验证的域名，该字段不会返回。
     */
    @JsonProperty(value = "invalid_wxa_server_domain")
    private String invalidWXAServerDomain;
}
