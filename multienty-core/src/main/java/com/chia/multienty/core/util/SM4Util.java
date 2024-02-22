package com.chia.multienty.core.util;

import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

import static org.bouncycastle.util.encoders.Base64.toBase64String;

public class SM4Util {
    public static final String ALGORITHM_NAME = "SM4";
    public static final String ALGORITHM_NAME_ECB_PADDING = "SM4/ECB/PKCS5Padding";

    // SM4算法目前只支持128位（即密钥16字节）
    public static final int DEFAULT_KEY_SIZE = 128;

    public static final String  ENCODING ="UTF-8";


    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 生成Base64编码的密钥
     *
     * @return Base64编码的密钥
     * @throws CryptoException
     */
    public static String generateKeyToBase64() throws CryptoException {
        return toBase64String(generateKey());
    }

    public static byte[] generateKey() throws CryptoException {
        return generateKey(DEFAULT_KEY_SIZE);
    }

    /**
     * 生成Base64编码的密钥
     *
     * @param keySize 密钥长度 单位为bit
     * @return Base64编码的密钥
     * @throws CryptoException
     */
    public static String generateKeyToBase64(int keySize) throws CryptoException {
        return toBase64String(generateKey(keySize));
    }

    public static byte[] generateKey(int keySize) throws CryptoException {
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        } catch (Exception e) {
            throw new CryptoException("SM4Util generateKey error:", e);
        }
        kg.init(keySize, new SecureRandom());
        return kg.generateKey().getEncoded();
    }


    public static byte[] encrypt_ECB_Padding(byte[] key, byte[] data) throws CryptoException {
        try {
            Cipher cipher = generateECBCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CryptoException("SM4Util encrypt_ECB_Padding error:", e);
        }
    }

    /**
     * ECB_PKCS5Padding 加密，返回Base64编码后的密文
     * @param key  base64
     * @param data 正常明文数据
     * @return
     * @throws CryptoException
     */
    public static String encrypt(String key, String data) throws CryptoException {
        try {
            Cipher cipher = generateECBCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.ENCRYPT_MODE, Base64.decode(key));
            byte[] enData =cipher.doFinal(data.getBytes(ENCODING));
            return toBase64String(enData);
        } catch (Exception e) {
            throw new CryptoException("SM4Util encrypt_ECB_Padding error:", e);
        }
    }

    /**
     * ECB_PKCS5Padding 解密 ,返回正常明文数据
     * @param key  base64
     * @param cipherText 返回Base64编码后的密文
     * @return
     * @throws CryptoException
     */
    public static String decrypt(String key, String cipherText) throws CryptoException {
        try {
            Cipher cipher = generateECBCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, Base64.decode(key));
            byte[] data =cipher.doFinal(Base64.decode(cipherText));
            return new String(data,ENCODING);
        } catch (Exception e) {
            throw new CryptoException("SM4Util decrypt_ECB_Padding error:", e);
        }
    }


    public static byte[] decrypt_ECB_Padding(byte[] key, byte[] cipherText) throws CryptoException {
        try {
            Cipher cipher = generateECBCipher(ALGORITHM_NAME_ECB_PADDING, Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(cipherText);
        } catch (Exception e) {
            throw new CryptoException("SM4Util decrypt_ECB_Padding error:", e);
        }
    }

    private static Cipher generateECBCipher(String algorithmName, int mode, byte[] key)
            throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException,
            InvalidKeyException {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        cipher.init(mode, sm4Key);
        return cipher;
    }

    public static void main(String[] args) {
        try {
            String key = "dRzPaYd7z6vYn9sL/JTZ3A==";

            String data="阿萨德哈的哦已我居然挤公交大幅度AAAADDF";

            String cipherText = encrypt(key, data);

            System.out.println("cipherText " + cipherText);

            String deData = decrypt(key, cipherText);

            System.out.println("deData " + deData);

        } catch (CryptoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
