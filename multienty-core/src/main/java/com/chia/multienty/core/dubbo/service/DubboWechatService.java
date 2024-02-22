package com.chia.multienty.core.dubbo.service;

import com.chia.multienty.core.domain.bo.WxLoginResponseBO;

public interface DubboWechatService {
    WxLoginResponseBO login(String appId, String jsCode);
}
