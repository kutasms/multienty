package com.chia.multienty.wechat.thirdparty.sdk.response.domain;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 设置第三方平台业务域名响应数据
 */
@Data
public class ThirdPartyJumpDomainModifyResponse extends BaseResponse {
    /**
     * 目前生效的 “全网发布版”第三方平台“小程序业务域名”。如果修改失败，该字段不会返回。如果没有已发布的第三方平台，该字段也不会返回。
     */
    @JsonProperty(value = "published_wxa_jump_h5_domain")
    private String publishedWXAJumpH5Domain;
    /**
     * 目前生效的 “测试版”第三方平台“小程序业务域名”。如果修改失败，该字段不会返回
     */
    @JsonProperty(value = "testing_wxa_jump_h5_domain")
    private String testingWXAJumpH5Domain;
    /**
     * 未通过验证的域名。如果不存在未通过验证的域名，该字段不会返回。
     */
    @JsonProperty(value = "invalid_wxa_jump_h5_domain")
    private String invalidWXAJumpH5Domain;
}
