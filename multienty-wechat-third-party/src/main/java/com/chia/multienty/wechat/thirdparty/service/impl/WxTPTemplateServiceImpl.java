package com.chia.multienty.wechat.thirdparty.service.impl;

import com.chia.multienty.wechat.thirdparty.sdk.request.template.AddToTemplateRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.template.TemplateDeleteRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.template.TemplateListGetRequest;
import com.chia.multienty.wechat.thirdparty.sdk.request.template.TemplatedRaftListGetRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.AddToTemplateResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplateDeleteResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplateListGetResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.template.TemplatedRaftListGetResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPTemplateService;
import com.chia.multienty.wechat.thirdparty.sdk.tools.WxTPApiExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WxTPTemplateServiceImpl implements WxTPTemplateService {
    private final WxTPApiExecutor executor;

    @Override
    public AddToTemplateResponse addToTemplate(Integer templateType, Long draftId) {
        AddToTemplateRequest request = new AddToTemplateRequest();
        request.setTemplateType(templateType);
        request.setDraftId(draftId);
        return executor.execute(request);
    }

    @Override
    public TemplateDeleteResponse deleteTemplate(String templateId) {
        TemplateDeleteRequest request = new TemplateDeleteRequest();
        request.setTemplateId(templateId);
        return executor.execute(request);
    }

    @Override
    public TemplatedRaftListGetResponse getTemplatedRaftList() {
        TemplatedRaftListGetRequest request = new TemplatedRaftListGetRequest();
        return executor.execute(request);
    }

    @Override
    public TemplateListGetResponse getTemplateList(Integer templateType) {
        TemplateListGetRequest request = new TemplateListGetRequest();
        request.setTemplateType(templateType);
        return executor.execute(request);
    }
}
