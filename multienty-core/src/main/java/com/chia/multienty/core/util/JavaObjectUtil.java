package com.chia.multienty.core.util;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class JavaObjectUtil {
    @SneakyThrows
    public static String object2UrlParams(Object obj) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = KutaBeanUtil.getAllDeclaredFields(obj.getClass());
        for(Field field: fields) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(obj);
            if(value != null) {
                if(sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(StringUtil.toUnderScoreCase(name), StandardCharsets.UTF_8.name()));
                sb.append("=");
                sb.append(URLEncoder.encode(value.toString(), StandardCharsets.UTF_8.name()));
            }
        }
        return sb.toString();
    }

}
