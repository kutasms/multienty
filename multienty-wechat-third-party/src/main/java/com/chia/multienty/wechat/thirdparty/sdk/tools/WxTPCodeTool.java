package com.chia.multienty.wechat.thirdparty.sdk.tools;

import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.wechat.thirdparty.define.code.ExtJson;
import com.chia.multienty.wechat.thirdparty.define.code.ExtObj;
import com.chia.multienty.wechat.thirdparty.parameter.code.CommitParameter;
import com.chia.multienty.wechat.thirdparty.sdk.request.code.CommitRequest;
import com.chia.multienty.wechat.thirdparty.sdk.response.code.CommitResponse;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizerAccessTokenGetResponse;
import com.chia.multienty.wechat.thirdparty.service.WxTPPlatformService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class WxTPCodeTool {
    @Autowired
    private YamlMultientyProperties properties;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WxTPApiExecutor executor;
    @Autowired
    private WxTPPlatformService wxTPPlatformService;
    @SneakyThrows
    public CommitResponse uploadCode(WechatApp app, CommitParameter parameter) {
        ExtObj extObj = new ExtObj();
        extObj.setProgramId(parameter.getProgramId().toString());
        extObj.setTenantId(app.getTenantId().toString());
        extObj.setAppId(app.getMiniAppId());
        extObj.setAppName(app.getAppNickName());

        ExtJson extJson = new ExtJson()
                .setExtAppId(app.getMiniAppId())
                .setExtEnable(true)
                .setExt(extObj);
        List<String> requiredPrivateInfos = properties.getWechat().getThirdParty().getRequiredPrivateInfos();

        if(!ListUtil.isEmpty(requiredPrivateInfos)) {
            extJson.setRequiredPrivateInfos(requiredPrivateInfos);
        }
        AuthorizerAccessTokenGetResponse tokenRsp = wxTPPlatformService
                .getAuthorizerAccessToken(app.getTenantId(), app.getProgramId());
        CommitRequest request = new CommitRequest();
        request.setExtJson(objectMapper.writeValueAsString(extJson));
        request.setUserVersion(parameter.getUserVersion());
        request.setUserDesc(parameter.getUserDesc());
        request.setTemplateId(parameter.getTemplateId());
        request.setAccessToken(tokenRsp.getAuthorizerAccessToken());
        return executor.execute(request);
    }
}
