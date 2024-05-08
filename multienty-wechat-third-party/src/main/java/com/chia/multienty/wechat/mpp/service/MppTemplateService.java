package com.chia.multienty.wechat.mpp.service;

import com.chia.multienty.core.domain.wechat.WxMppTemplateType;

public interface MppTemplateService {
    String sendScribeMessage(Long programId, Long tenantId, WxMppTemplateType templateType, Object data, String subOpenId);
}
