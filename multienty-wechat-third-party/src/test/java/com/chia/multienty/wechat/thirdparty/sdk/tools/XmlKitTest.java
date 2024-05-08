package com.chia.multienty.wechat.thirdparty.sdk.tools;

import com.chia.multienty.core.util.RandomStringUtils;
import com.chia.multienty.wechat.official.domain.entity.OfficialReplyMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class XmlKitTest {

    @Test
    void toXml() throws AesException {
        OfficialReplyMessage replyMessage = new OfficialReplyMessage();
        replyMessage.setContent("TESTCOMPONENT_MSG_TYPE_TEXT_callback");
        replyMessage.setCreateTime(1713545118);
        replyMessage.setFrom("gh_3c884a361561");
        replyMessage.setTo("ozy4qt5QUADNXORxCVipKMV9dss0");
        replyMessage.setMsgType("text");
        String s = XmlKit.toXml(replyMessage);
        log.info(s);

        String aesKey = "fi3817vj0lzj9zj9fV46jljbs76412ijgiv9Zjk3mmW";
        String token = "if823n48v7ah3ivhan";
        String appId = "wxffca9a2c0ba6db08";
        String nonce = RandomStringUtils.getRandomCode(6, 6);
        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, aesKey, appId);
        String result = pc.encryptMsg(s, null, nonce);
        log.info("加密后:{}", result);

    }

    @Test
    void parse() {
    }

    @Test
    void getInfoType() {
    }

    @Test
    void getMsgType() {
    }

    @Test
    void getSpec() {
    }
}