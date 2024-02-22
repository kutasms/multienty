package com.chia.multienty.core.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.Map;

public class MapUtil {

    @SneakyThrows
    public static <T, K,V> T get(Class<T> clazz, Map<K,V> map, K key) {
        V value = map.get(key);
        if(value == null) {
            return null;
        }
        ObjectMapper objectMapper = SpringUtil.getBean(ObjectMapper.class);
        return objectMapper.readValue(objectMapper.writeValueAsString(value), clazz);
    }

    @SneakyThrows
    public static <T, K,V> T get(TypeReference<T> typeReference, Map<K,V> map, K key) {
        V value = map.get(key);
        if(value == null) {
            return null;
        }
        ObjectMapper objectMapper = SpringUtil.getBean(ObjectMapper.class);
        return objectMapper.readValue(objectMapper.writeValueAsString(value), typeReference);
    }
}
