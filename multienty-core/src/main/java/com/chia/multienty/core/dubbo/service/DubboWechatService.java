package com.chia.multienty.core.dubbo.service;

import com.chia.multienty.core.domain.bo.WxLoginResponseBO;
import com.chia.multienty.core.domain.dto.WechatAppDTO;
import com.chia.multienty.core.domain.wechat.WxMppTemplateType;
import com.chia.multienty.core.parameter.wechat.WechatAppDetailGetParameter;
import com.chia.multienty.core.strategy.pay.domain.PayType;

public interface DubboWechatService {
    WechatAppDTO getWechatApp(WechatAppDetailGetParameter parameter);

    PayType getPayType(Long programId);
    WxLoginResponseBO login(String appId, String jsCode);

    String sendScribeMessage(Long tenantId, Long programId, WxMppTemplateType templateType, String subOpenId, Object data);
}
