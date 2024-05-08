package com.chia.multienty.wechat.thirdparty.sdk.tools;

import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.wechat.thirdparty.define.WxThirdPartyConstants;
import com.chia.multienty.wechat.thirdparty.sdk.request.platform.ComponentAccessTokenGetRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.platform.PreAuthCodeGetRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.ComponentAccessTokenGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.PreAuthCodeGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class WxTPTokenProvider {

    @Autowired
    private YamlMultientyProperties properties;
    @Autowired
    private StringRedisService stringRedisService;
    @Autowired
    private WxTPApiExecutor executor;

    /**
     * 获取预授权码
     */
    public String getPreAuthCode() {
        PreAuthCodeGetRequest request = new PreAuthCodeGetRequest();
        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        PreAuthCodeGetResponse response = executor.execute(request);
        return response.getPreAuthCode();
    }

    /**
     * 获取第三方平台access token
     */
    public String getComponentAccessToken() {
        Object objInCache = stringRedisService.get(WxThirdPartyConstants.CACHE_KEY_COMPONENT_ACCESS_TOKEN);
        if(objInCache != null) {
            return objInCache.toString();
        }
        ComponentAccessTokenGetRequest request = new ComponentAccessTokenGetRequest();
        request.setComponentAppId(properties.getWechat().getThirdParty().getAppId());
        request.setComponentAppSecret(properties.getWechat().getThirdParty().getAppSecret());
        request.setComponentVerifyTicket(
                stringRedisService.get(WxThirdPartyConstants.CACHE_KEY_COMPONENT_VERIFY_TICKET).toString());
        ComponentAccessTokenGetResponse response = executor.execute(request);
        if(response.isSucceed()) {
            stringRedisService.set(WxThirdPartyConstants.CACHE_KEY_COMPONENT_ACCESS_TOKEN,
                    response.getComponentAccessToken(), (int)(response.getExpiresIn() * 0.9) * 1000);
        }
        return response.getComponentAccessToken();
    }
}
