package com.chia.multienty.wechat.thirdparty.service;

import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterBindResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.member.TesterUnbindResponse;

public interface WxTPMemberService {
    TesterBindResponse bindTester(String appId, String wechatId);

    TesterGetResponse getTester(String appId, String action);

    TesterUnbindResponse unbindTester(String appId, String wechatId, String userStr);
}
