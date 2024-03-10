
package com.chia.multienty.core.cache.redis.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;

public interface StringRedisService {


    boolean set(String key, String value);

    boolean setJson(String key, Object value);

    boolean setJson(String key, Object value, long time);

    public boolean set(String key, String value, long time);

    public long incr(String key, long delta);

    public long decr(String key, long delta);

    public Object get(String key);

    <T> T get(String key, Class<T> clazz) throws IOException;

    <T> T get(String key, TypeReference<T> reference) throws JsonProcessingException;

    public void delete(String key);

    public boolean isExist(String key);
    boolean lock(String key, String value, long expire);

    public boolean unlock(String key, String value);

    public void deleteAll();
}
