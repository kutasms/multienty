
package com.chia.multienty.core.cache.redis.service.api.impl;


import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.cache.redis.RedisConfiguration;
import com.chia.multienty.core.cache.redis.service.api.CommonRedisService;
import com.chia.multienty.core.cache.redis.service.api.HashRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@ConditionalOnClass(RedisConfiguration.class)
public class HashRedisServiceImpl implements HashRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CommonRedisService commonRedisService;

    @Override
    public Object hget(Object key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    @Override
    public <HK, HV> List<HV> getMulti(HK key, String... items) {
        return redisTemplate.<String, HV>opsForHash().multiGet(key, Arrays.asList(items));
    }

    @Override
    public Map<String, Object> hgetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public <T> T hgetAll(String key, Class<T> clazz) {
        Map map = hgetAll(key);
        if(map == null) {
            return null;
        }
//        BeanUtils.populate(t, map);
        String s = JSONObject.toJSONString(map);
        return JSONObject.parseObject(s, clazz);
    }

    @Override
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if(time>0){
               commonRedisService.expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if(time>0){
                commonRedisService.expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key,item);
    }

    @Override
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    @Override
    public double hincr(String key, String item, double val) {
        return redisTemplate.opsForHash().increment(key, item, val);
    }

    @Override
    public long incr(String key, String item, long val) {
        return redisTemplate.opsForHash().increment(key, item, val);
    }

    @Override
    public double hdecr(String key, String item, double val) {
        return redisTemplate.opsForHash().increment(key, item,-val);
    }

    @Override
    public long decr(String key, String item, long val) {
        return redisTemplate.opsForHash().increment(key, item, -val);
    }

}
