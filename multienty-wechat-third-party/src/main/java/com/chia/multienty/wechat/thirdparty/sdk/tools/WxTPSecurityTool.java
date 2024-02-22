package com.chia.multienty.wechat.thirdparty.sdk.tools;

import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.util.RandomStringUtils;
import com.chia.multienty.core.util.SpringUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

@Slf4j
@RequiredArgsConstructor
public class WxTPSecurityTool {


    @SneakyThrows
    public static String encrypt(String replyMsg) {
        YamlMultiTenantProperties properties = SpringUtil.getBean(YamlMultiTenantProperties.class);
        String aesKey = properties.getWechat().getThirdParty().getMessageDecryptKey();
        String token = properties.getWechat().getThirdParty().getMessageVerifyToken();
        String appId = properties.getWechat().getThirdParty().getAppId();
        String nonce = RandomStringUtils.getRandomCode(6, 6);
        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, aesKey, appId);
        String result = pc.encryptMsg(replyMsg, null, nonce);
        log.info("加密后:{}", result);
        return result;
    }

    @SneakyThrows
    public static String decrypt(String msg) {
        YamlMultiTenantProperties properties = SpringUtil.getBean(YamlMultiTenantProperties.class);
        String aesKey = properties.getWechat().getThirdParty().getMessageDecryptKey();
        String token = properties.getWechat().getThirdParty().getMessageVerifyToken();
        String appId = properties.getWechat().getThirdParty().getAppId();
        String nonce = RandomStringUtils.getRandomCode(6, 6);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(msg);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);

        Element root = document.getDocumentElement();
        NodeList nodeListEncrypt = root.getElementsByTagName("Encrypt");
        NodeList nodeListMsgSignature = root.getElementsByTagName("MsgSignature");

        String encrypt = nodeListEncrypt.item(0).getTextContent();
        String msgSignature = nodeListMsgSignature.item(0).getTextContent();

        String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
        String fromXML = String.format(format, encrypt);

        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, aesKey, appId);
        //
        // 公众平台发送消息给第三方，第三方处理
        //

        // 第三方收到公众号平台发送的消息
        String result = pc.decryptMsg(msgSignature, null, nonce, fromXML);
        log.info("解密后明文: {}", result);
        return result;
    }
}
