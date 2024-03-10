
package com.chia.multienty.core.cache.redis.service.api.impl;


import com.chia.multienty.core.cache.redis.RedisConfiguration;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@ConditionalOnClass(RedisConfiguration.class)
public class StringRedisServiceImpl implements StringRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static final long EXPIRE = 1000L;

    private static final long TIMEOUT = 1000L;

    @Override
    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean setJson(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(value));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean setJson(String key, Object value, long time) {
        try {
            redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(value),time, TimeUnit.MILLISECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean set(String key, String value, long time) {
        try {
            if(time>0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.MILLISECONDS);
            }else{
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public long incr(String key, long delta) {
        if(delta<0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public long decr(String key, long delta) {
        if(delta<0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    @Override
    public Object get(String key) {
        try {
            return key == null ? null : redisTemplate.opsForValue().get(key);
        }
        catch (SerializationException ex) {
            redisTemplate.delete(key);
        }
        return null;
    }
    @Override
    public <T> T get(String key, Class<T> clazz) throws IOException {
        if(key == null) {
            return null;
        }
        Object result = redisTemplate.opsForValue().get(key);
        if(result instanceof String && result != null) {
            return objectMapper.readValue(result.toString(), clazz);
        }
        return result == null ? null : objectMapper.convertValue(result, clazz);
    }

    @Override
    public <T> T get(String key, TypeReference<T> reference) throws JsonProcessingException {
        if(key == null) {
            return null;
        }
        Object result = redisTemplate.opsForValue().get(key);
        if(result instanceof String && result != null) {
            return objectMapper.readValue(result.toString(), reference);
        }
        return result == null ? null : objectMapper.convertValue(result, reference);
    }

    @Override
    public void delete(String key){
        redisTemplate.delete(key);
    }

    @Override
    public boolean isExist(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 修正过的分布式锁逻辑
     * */
    @Override
    public boolean lock(String key, String value, long expire) {
        RedisCallback<Boolean> redisCallback = connection -> {
            RedisStringCommands.SetOption setOption = RedisStringCommands.SetOption.ifAbsent();
            Expiration expiration = Expiration.milliseconds(expire);
//            byte[] keyByte = redisTemplate.getKeySerializer().serialize(key);
//            byte[] valueByte = redisTemplate.getValueSerializer().serialize(value);
            Boolean result = connection.set(key.getBytes(), value.getBytes(), expiration, setOption);
            return result;
        };
        return (Boolean) redisTemplate.execute(redisCallback);
    }
    /**
     * 修正过的分布式解锁
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean unlock(String key, String value){
        String script =
                "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                        "   return redis.call('del',KEYS[1]) " +
                        "else" +
                        "   return 0 " +
                        "end";

        RedisScript<Boolean> redisScript = RedisScript.of(script, Boolean.class);
        return (Boolean) redisTemplate.execute(redisScript, Arrays.asList(key), value);
    }

    /**
     * 清空所有key
     */
    @Override
    public void deleteAll() {
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }
}
