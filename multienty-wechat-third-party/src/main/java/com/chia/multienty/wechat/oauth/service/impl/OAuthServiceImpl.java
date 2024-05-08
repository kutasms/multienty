package com.chia.multienty.wechat.oauth.service.impl;

import cn.hutool.http.HttpUtil;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.wechat.oauth.define.WechatOAuthResult;
import com.chia.multienty.wechat.oauth.service.OAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService {
    private final String PATTERN_ACCESS_TOKEN_GET_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    private final YamlMultientyProperties properties;
    private final ObjectMapper objectMapper;
    private final StringRedisService stringRedisService;
    private final String PATTERN_CACHE_KEY = "TENANT_WX_OAUTH_%s";
    @SneakyThrows
    @Override
    public WechatOAuthResult getAccessToken(String code) {
        String url = String.format(PATTERN_ACCESS_TOKEN_GET_URL,
                properties.getWechat().getPc().getAppId(),
                properties.getWechat().getPc().getAppSecret(),
                code
        );
        String response = HttpUtil.get(url);
        WechatOAuthResult wechatOAuthResult = objectMapper.readValue(response, WechatOAuthResult.class);
        String cacheKey = String.format(PATTERN_CACHE_KEY, MultientyContext.getTenant().getTenantId());
        stringRedisService.setJson(cacheKey, wechatOAuthResult, ((wechatOAuthResult.getExpiresIn().longValue()) - 60) * 1000);
        return wechatOAuthResult;
    }
    @Override
    @SneakyThrows
    public WechatOAuthResult getAccessToken() {
        String cacheKey = String.format(PATTERN_CACHE_KEY, MultientyContext.getTenant().getTenantId());
        return stringRedisService.get(cacheKey, WechatOAuthResult.class);
    }
}
