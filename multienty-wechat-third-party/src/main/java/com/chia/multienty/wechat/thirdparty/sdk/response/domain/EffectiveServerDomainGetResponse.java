package com.chia.multienty.wechat.thirdparty.sdk.response.domain;

import com.chia.multienty.wechat.thirdparty.sdk.response.BaseResponse;
import com.chia.multienty.wechat.thirdparty.define.domain.MppDomainConfig;
import lombok.Data;

/**
 * 获取发布后生效服务器域名列表响应数据
 */
@Data
public class EffectiveServerDomainGetResponse extends BaseResponse {
    /**
     * 通过公众平台配置的服务器域名列表
     */
    private MppDomainConfig mpDomain;
    /**
     * 通过第三方平台接口modify_domain 配置的服务器域名
     */
    private MppDomainConfig thirdDomain;
    /**
     * 通过“modify_domain_directly”接口配置的服务器域名列表
     */
    private MppDomainConfig directDomain;
    /**
     * 最后提交代码或者发布上线后生效的域名列表
     */
    private MppDomainConfig effectiveDomain;
}
