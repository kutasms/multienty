package com.chia.multienty.core.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

@Slf4j
class RsaUtilTest {
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjgpCvt/I99QL+IEFKMffCZwUytatFVUN+cJKCFJFktLzH2l61ysc2nfWVMbmn+mRFodL+fmb9rbufHxFvvZC+Ee8csz93s5QKB8ocx8Iqb6Qh9+ji6KfcydtrF6XsVldVSaleek7dQUgXwNgDWMYCd8ci5BhggWegUAoBUH6ycnLJ71Y5LNfk+GLu+bcNow5bMWQvM/YQviJ9fFRPt3rkiyxU7lJhb7VMw8dQmbkvpM9IhIFFJZbWBFPzXxNRs4Bpx+jPpyhROSdoN04fUFKV1bn+WmUxkjeV0k/jojS8j0HGOH/VXYd66xEv05sjaVZz+8eW6b6ml8BJfNgBsWcwwIDAQAB";
    @Test
    void decryptByPublicKey() {
    }

    @Test
    void encryptByPrivateKey() throws Exception {

    }

    @Test
    void decryptByPrivateKey() {
    }

    @Test
    void encryptByPublicKey() throws Exception {
        log.info("公钥长度:{}", publicKey.length());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "admin");
        jsonObject.put("password", "123456");
//        jsonObject.put("code", "5082");
        jsonObject.put("mode", 1);
        String text = jsonObject.toJSONString();
        log.info("明文长度:{}",text.getBytes().length);
        String encrypted = RsaUtil.encryptByPublicKey(
                publicKey, text);
        log.info("加密后数据:{}", encrypted);
    }

    @Test
    void generateKeyPair() throws NoSuchAlgorithmException {
        RsaUtil.RsaKeyPair rsaKeyPair = RsaUtil.generateKeyPair();
        log.info("RSA 2048 PUBLIC KEY:{}",rsaKeyPair.getPublicKey());
        log.info("RSA 2048 PRIVATE KEY:{}",rsaKeyPair.getPrivateKey());
    }
}