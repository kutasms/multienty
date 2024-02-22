package com.chia.multienty.core.util;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.domain.wechat.WxDataWrapper;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class WxUtil {
    public static final String AES = "AES";
    public static final String AES_CBC_PADDING = "AES/CBC/PKCS7Padding";
    public WxDataWrapper getSessionKeyOrOpenId(String code, String appId, String appSecret) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, Object> requestUrlParam = new HashMap<>();

        //小程序appId
        requestUrlParam.put("appid", appId);
        //小程序secret
        requestUrlParam.put("secret", appSecret);
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        WxDataWrapper wxDataWrapper = JSON.parseObject(HttpUtil.get(requestUrl, requestUrlParam), WxDataWrapper.class);
        return wxDataWrapper;
    }

    public JSONObject getAccessToken(String appId, String appSecret){
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> requestUrlParam = new HashMap<>();
        //小程序appId
        requestUrlParam.put("appid",  appId);
        //小程序secret
        requestUrlParam.put("secret", appSecret);
        //默认参数
        requestUrlParam.put("grant_type", "client_credential");
        JSONObject jsonObject = JSON.parseObject(HttpUtil.get(requestUrl, requestUrlParam));
        return jsonObject;
    }

    public static String wxDecrypt(String encrypted, String sessionKey, String iv) {
        String result = null;
        byte[] encrypted64 = org.apache.commons.codec.binary.Base64.decodeBase64(encrypted);
        byte[] key64 = org.apache.commons.codec.binary.Base64.decodeBase64(sessionKey);
        byte[] iv64 = org.apache.commons.codec.binary.Base64.decodeBase64(iv);
        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyGenerator.getInstance(AES).init(128);
            result = new String(decrypt(encrypted64, key64, generateIV(iv64)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 生成iv
     */
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        // iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
        // Arrays.fill(iv, (byte) 0x00);
        AlgorithmParameters params = AlgorithmParameters.getInstance(AES);
        params.init(new IvParameterSpec(iv));
        return params;
    }
    /**
     * 生成解密
     */
    public static byte[] decrypt(byte[] encryptedData, byte[] keyBytes, AlgorithmParameters iv)
            throws Exception {
        Key key = new SecretKeySpec(keyBytes, AES);
        Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
        // 设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(encryptedData);
    }

    public WxDataWrapper getPhoneNumber(String encryptedData, String sessionKey, String iv) {
        String result = wxDecrypt(encryptedData, sessionKey, iv);
        log.info("手机号解密:{}", result);
        return JSONObject.parseObject(result, WxDataWrapper.class);
    }
}
