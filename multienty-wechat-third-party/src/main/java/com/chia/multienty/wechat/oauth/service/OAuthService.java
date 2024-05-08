package com.chia.multienty.wechat.oauth.service;

import com.chia.multienty.wechat.oauth.define.WechatOAuthResult;

public interface OAuthService {
    WechatOAuthResult getAccessToken(String code);

    WechatOAuthResult getAccessToken();
}
