package com.chia.multienty.wechat.thirdparty.sdk.request.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 公众号授权链接生成请求
 */
@Data
public class OfficialAccountAuthLinkGenerateRequest {
    /**
     * 第三方平台的 appid
     */
    @JsonProperty(value = "component_appid")
    private String componentAppId;
    /**
     * 公众号的 appid
     */
    @JsonProperty(value = "appid")
    private String appId;
    /**
     * 是否复用公众号的资质进行微信认证(1:申请复用资质进行微信 认证 0:不申请)
     */
    private Integer copyWxVerify;
    /**
     * 用户扫码授权后，MP 扫码页面将跳转到该地址(注:1.链接需 urlencode
     * 2.Host 需和第三方平台在微信开放平台上面填写的登 录授权的发起页域名一致)
     */
    private String redirectUri;
}
