package com.chia.multienty.wechat.thirdparty.sdk.response.domain;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 获取发布后生效业务域名列表响应数据
 */
@Data
public class EffectiveJumpDomainGetResponse extends BaseResponse {
    /**
     * 通过公众平台配置的服务器域名列表
     */
    @JsonProperty(value = "mp_webviewdomain")
    private List<String> mpWebViewDomain;
    /**
     * 通过第三方平台接口modifyServerDomain 配置的服务器域名
     */
    @JsonProperty(value = "third_webviewdomain")
    private List<String> thirdWebViewDomain;
    /**
     * 通过modifyJumpDomainDirectly接口配置的业务域名列表
     */
    @JsonProperty(value = "direct_webviewdomain")
    private List<String> directWebViewDomain;
    /**
     * 最后提交代码或者发布上线后生效的域名列表
     */
    @JsonProperty(value = "effective_webviewdomain")
    private List<String> effectiveWebViewDomain;
}
