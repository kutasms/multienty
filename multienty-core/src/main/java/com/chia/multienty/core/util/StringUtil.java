/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.chia.multienty.core.util;

import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zheng Jie
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {

    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);
    private static boolean ipLocal = false;
    private static File file = null;
    private static final char SEPARATOR = '_';

    private static final char HYPHEN = '-';
    private static final String UNKNOWN = "unknown";

    public static String camelCase(String s) {
        if(s == null || s.length() < 2) {
            return s;
        }
        char c = Character.toLowerCase(s.charAt(0));
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append(s.substring(1, s.length()));
        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 转换为带横杠的小写名称<br/>
     * toHyphenCase("helloWorld") = "hello-world"
     * @param s
     * @return
     */
    public static String toHyphenCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(HYPHEN);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @return toCamelCase(" hello_world ") == "helloWorld"
     * toCapitalizeCamelCase("hello_world") == "HelloWorld"
     * toUnderScoreCase("helloWorld") = "hello_world"
     */
    static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String comma = ",";
        String localhost = "127.0.0.1";
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }

    /**
     * 获得当天是周几
     */
    public static String getWeekDay() {
        String[] weekDays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取当前机器的IP
     *
     * @return /
     */
    public static String getLocalIp() {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements();) {
                NetworkInterface anInterface = interfaces.nextElement();
                // 在所有的接口下再遍历IP
                for (Enumeration<InetAddress> inetAddresses = anInterface.getInetAddresses(); inetAddresses.hasMoreElements();) {
                    InetAddress inetAddr = inetAddresses.nextElement();
                    // 排除loopback类型地址
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr.getHostAddress();
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress.getHostAddress();
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                return "";
            }
            return jdkSuppliedAddress.getHostAddress();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 检测文字的长度，汉字算一个字，其它算半个字
     * @param text
     * @param min
     * @param max
     * @return
     */
    public static boolean checkLength(String text, int min, int max) {
        BigDecimal length = BigDecimal.ZERO;
        if (StringUtil.isBlank(text) || text.length() < min) {
            return false;
        }
        char[] arr = text.toCharArray();
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        for (char c:arr) {
            Matcher m = p.matcher(String.valueOf(c));
            if (m.find()) {
                length = length.add(BigDecimal.ONE);
            } else {
                length = length.add(BigDecimal.valueOf(0.5));
            }
        }
        return length.compareTo(new BigDecimal(min)) >= 0 && length.compareTo(new BigDecimal(max)) <= 0;
    }

    /**
     * 对电话号码打码
     * @param phone
     * @return
     */
    public static String encodePhone(String phone) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(phone)) {
            if (phone.length() <= 1) {
                return "*";
            } else if (phone.length() <= 3) {
                String head = phone.substring(0,1);
                for (int i=0;i<3-phone.length();i++) {
                    head = head + "*";
                }
                return head;
            } else if (phone.length() < 8) {
                String head = phone.substring(0,3);
                for(int i=0;i<phone.length()-3;i++) {
                    head = head + "*";
                }
                return head;
            } else {
                String head = phone.substring(0, 3);
                for (int i=0;i<4;i++) {
                    head = head + "*";
                }
                return head + phone.substring(7);
            }
        }
        return phone;
    }

    public static String maskStr(String s,int maskLen) {
        if (!TextUtils.isEmpty(s) && s.length() > maskLen) {
            if(s.length() > maskLen) {
                char[] a = s.toCharArray();
                for (int i = (a.length - maskLen) / 2; i < (a.length + maskLen) / 2 ; i++) {
                    a[i] = '*';
                }
                return String.valueOf(a);
            }
            else {
                return s.replace("\\w*?", "*");
            }
        }
        return s;
    }
}
