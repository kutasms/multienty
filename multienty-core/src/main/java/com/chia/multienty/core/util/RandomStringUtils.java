
package com.chia.multienty.core.util;

import lombok.SneakyThrows;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @Name RandomStringUtil
 * @Descr 生成随机字符串
 */
public class RandomStringUtils {
    /**
     * 随机生成字符串
     * <table>
     *     <tr>
     *         <th width="40px">类型</th>
     *         <th>说明</th>
     *     </tr>
     *     <tr>
     *         <td>0</td>
     *         <td>纯数字(0-9)</td>
     *     </tr>
     *     <tr>
     *         <td>1</td>
     *         <td>全小写字母(a-z)</td>
     *     </tr>
     *     <tr>
     *         <td>2</td>
     *         <td>全大写字母(A-Z)</td>
     *     </tr>
     *     <tr>
     *         <td>3</td>
     *         <td>数字+小写字母</td>
     *     </tr>
     *     <tr>
     *         <td>4</td>
     *         <td>数字+大写字母</td>
     *     </tr>
     *     <tr>
     *         <td>5</td>
     *         <td>大写字母+小写字母</td>
     *     </tr>
     *     <tr>
     *         <td>6</td>
     *         <td>数字+大写字母+小写字母</td>
     *     </tr>
     *     <tr>
     *         <td>7</td>
     *         <td>固定长度33位：根据UUID拿到的随机字符串，去掉了四个"-"(相当于长度33位的小写字母加数字)</td>
     *     </tr>
     * </table>
     *
     * @param passLength
     *            : 要生成多少长度的字符串
     * @param type
     *            : 类型
     * @return 根据传入的type来判定
     */
    @SneakyThrows
    public static String getRandomCode(int passLength, int type)  {
        StringBuffer buffer = null;
        StringBuffer sb = new StringBuffer();
        Random r = SecureRandom.getInstanceStrong();

        r.setSeed(new Date().getTime());
        switch (type) {
        case 0:
            buffer = new StringBuffer("0123456789");
            break;
        case 1:
            buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
            break;
        case 2:
            buffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            break;
        case 3:
            buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
            break;
        case 4:
            buffer = new StringBuffer("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            break;
        case 5:
            buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
            break;
        case 6:
            buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
            sb.append(buffer.charAt(r.nextInt(buffer.length() - 10)));
            passLength -= 1;
            break;
        case 7:
            String s = UUID.randomUUID().toString();
            sb.append(s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24));
        }

        if (type != 7) {
            int range = buffer.length();
            for (int i = 0; i < passLength; ++i) {
                sb.append(buffer.charAt(r.nextInt(range)));
            }
        }
        return sb.toString();
    }


    public static String getRandom() {
        String num = "";
        for (int i = 0; i < 4; i++) {
            num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
        }
        return num;
    }
}
