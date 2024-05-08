package com.chia.multienty.wechat.mpp.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.domain.wechat.WxMppTemplateType;
import com.chia.multienty.core.domain.wechat.WxSubscribeMessage;
import com.chia.multienty.core.pojo.WechatApp;
import com.chia.multienty.core.pojo.WechatMppTemplate;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.WechatAppService;
import com.chia.multienty.core.service.WechatMppTemplateService;
import com.chia.multienty.wechat.mpp.service.MppTemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class MppTemplateServiceImpl implements MppTemplateService {
    private final String SUBSCRIBE_URL = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=%s";
    private final WechatAppService wechatAppService;
    private final WechatMppTemplateService wechatMppTemplateService;
    private final YamlMultientyProperties properties;
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String sendScribeMessage(Long programId, Long tenantId, WxMppTemplateType templateType, Object data, String subOpenId)
    {
        WechatMppTemplate template = wechatMppTemplateService.getOne(tenantId, programId, templateType);
        if(template == null) {
            log.info("租户{}的微信小程序{}未配置{}模版，跳过发送模版消息", tenantId, programId, templateType.getDescribe());
            return null;
        }
        WechatApp wechatApp = wechatAppService.getByIdAndSharding(
                new WechatApp().setProgramId(programId).setTenantId(tenantId));
        WxSubscribeMessage<T> message = new WxSubscribeMessage<T>();
        message.setTemplateId(template.getTemplateId());
        message.setLang(properties.getWechat().getMpp().getLang());
        message.setMiniProgramState(properties.getWechat().getMpp().getEnv());
        message.setReceiver(subOpenId);
        String json = null;
        try {
            json = objectMapper.writeValueAsString(data);
            Map<String, Object> map = null;
            map = objectMapper.readValue(json, Map.class);
            JSONObject jsonObj = new JSONObject();
            map.forEach((k,v) -> {
                JSONObject valJson = new JSONObject();
                valJson.put("value", v);
                jsonObj.put(k.toString(), valJson);
            });
            message.setData(jsonObj);
            String params = JSONObject.toJSONString(message, true);
            log.info(params);
            String result = HttpUtil.post(String.format(SUBSCRIBE_URL, wechatApp.getAuthorizerAccessToken()), params);
            log.info(result);
            return result;
        } catch (JsonProcessingException e) {
            log.error("",e);
            throw e;
        }
    }
}
