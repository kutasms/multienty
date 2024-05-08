package com.chia.multienty.wechat.official.service.impl;

import com.chia.multienty.core.domain.basic.HttpResult;
import com.chia.multienty.core.tools.MapBuilder;
import com.chia.multienty.core.util.HttpUtil;
import com.chia.multienty.wechat.official.domain.entity.CustomMessage;
import com.chia.multienty.wechat.official.domain.request.OfficialMessageRequest;
import com.chia.multienty.wechat.official.service.IMService;
import com.chia.multienty.wechat.thirdparty.sdk.response.platform.AuthorizationInfoGetByAuthCodeResponse;
import com.chia.multienty.wechat.thirdparty.sdk.tools.XmlKit;
import com.chia.multienty.wechat.thirdparty.service.WxTPPlatformService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IMServiceImpl implements IMService {
    @Autowired
    private WxTPPlatformService wxTPPlatformService;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    @SneakyThrows
    @Async
    public void handleMessageReceived(final String decryptedMsg, final String openId) {
        log.info("开始处理客服消息");
        OfficialMessageRequest request = XmlKit.parse(decryptedMsg, OfficialMessageRequest.class);
        String code = request.getContent().replace("QUERY_AUTH_CODE:","");
        AuthorizationInfoGetByAuthCodeResponse authCodeResponse =
                wxTPPlatformService.getAuthorizationInfoByAuth(code);

        String reply = code + "_from_api";
        CustomMessage message = new CustomMessage();
        message.setToUser(request.getFrom());
        message.setText(MapBuilder.<String, Object>create().add("content", reply).get());
        String replyMsg = objectMapper.writeValueAsString(message);
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
        url = String.format(url, authCodeResponse.getAuthorizationInfo().getAuthorizerAccessToken());
        HttpResult result = HttpUtil.doPostJson(url, replyMsg);
        log.info("回复客服消息,url:{}, replyMsg:{}, result:{}", url, replyMsg,
                objectMapper.writeValueAsString(result));
    }
}
