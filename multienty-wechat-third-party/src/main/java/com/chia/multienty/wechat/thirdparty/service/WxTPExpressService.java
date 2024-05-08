package com.chia.multienty.wechat.thirdparty.service;

import com.chia.multienty.wechat.thirdparty.sdk.response.express.MsgPluginApplyResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.express.QueryPluginApplyResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.express.ReturnPluginApplyResponse;

public interface WxTPExpressService {
    MsgPluginApplyResponse applyMsgPlugin(String appId);

    QueryPluginApplyResponse applyQueryPlugin(String appId);

    ReturnPluginApplyResponse applyReturnPlugin(String appId);
}
