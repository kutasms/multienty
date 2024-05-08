package com.chia.multienty.wechat.thirdparty.sdk.tools;

import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
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
        YamlMultientyProperties properties = SpringUtil.getBean(YamlMultientyProperties.class);
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
    public static String decrypt(String msg,
                                 String signature,
                                 String timestamp,
                                 String nonce,
                                 String encryptType,
                                 String msgSignature) {
        YamlMultientyProperties properties = SpringUtil.getBean(YamlMultientyProperties.class);
        String aesKey = properties.getWechat().getThirdParty().getMessageDecryptKey();
        String token = properties.getWechat().getThirdParty().getMessageVerifyToken();
        String appId = properties.getWechat().getThirdParty().getAppId();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(msg);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);

        Element root = document.getDocumentElement();
        NodeList nodeListEncrypt = root.getElementsByTagName("Encrypt");
        String encrypt = nodeListEncrypt.item(0).getTextContent();

        String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
        String fromXML = String.format(format, encrypt);
        if(encryptType.equals("aes")) {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, aesKey, appId);
            //
            // 公众平台发送消息给第三方，第三方处理
            //
            // 第三方收到公众号平台发送的消息
            String result = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
            log.info("解密后明文: {}", result);
            return result;
        }
        throw new KutaRuntimeException(HttpResultEnum.PATTERN_SERVICE_NOT_IMPLEMENT,encryptType + "解密服务");
    }
}
