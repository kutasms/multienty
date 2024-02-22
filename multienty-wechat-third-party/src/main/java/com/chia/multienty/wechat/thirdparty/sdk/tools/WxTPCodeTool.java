package com.chia.multienty.wechat.thirdparty.sdk.tools;

import com.chia.multienty.core.domain.basic.KeyValuePair;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.wechat.thirdparty.define.code.ExtJson;
import com.chia.multienty.wechat.thirdparty.define.template.MppCodeTemplate;
import com.chia.multienty.wechat.thirdparty.sdk.request.code.CommitRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.CommitResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

@RequiredArgsConstructor
public class WxTPCodeTool {
    private final YamlMultiTenantProperties properties;
    private final ObjectMapper objectMapper;
    private final WxTPApiExecutor executor;
    @SneakyThrows
    public CommitResponse uploadCode(WechatApp app, MppCodeTemplate template) {
        ExtJson extJson = new ExtJson()
            .setExtAppId(app.getMiniAppId())
                .setExtEnable(true)
                .setExt(new KeyValuePair<>().setKey("tenant_id").setValue(app.getTenantId()));
        List<String> requiredPrivateInfos = properties.getWechat().getThirdParty().getRequiredPrivateInfos();

        if(!ListUtil.isEmpty(requiredPrivateInfos)) {
            extJson.setRequiredPrivateInfos(requiredPrivateInfos);
        }

        CommitRequest request = new CommitRequest();
        request.setExtJson(objectMapper.writeValueAsString(extJson));
        request.setUserVersion(template.getUserVersion());
        request.setUserDesc(template.getUserDesc());
        request.setTemplateId(template.getTemplateId());
        request.setAccessToken(app.getAuthorizerAccessToken());
        return executor.execute(request);
    }
}
