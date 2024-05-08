package com.chia.multienty.wechat.thirdparty.service;

import com.chia.multienty.wechat.thirdparty.sdk.response.template.AddToTemplateResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplateDeleteResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplateListGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplatedRaftListGetResponse;

public interface WxTPTemplateService {
    AddToTemplateResponse addToTemplate(Integer templateType, Long draftId);

    TemplateDeleteResponse deleteTemplate(String templateId);

    TemplatedRaftListGetResponse getTemplatedRaftList();

    TemplateListGetResponse getTemplateList(Integer templateType);
}
