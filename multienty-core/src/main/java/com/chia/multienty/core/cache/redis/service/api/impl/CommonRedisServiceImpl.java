
package com.chia.multienty.core.cache.redis.service.api.impl;

import com.chia.multienty.core.cache.redis.RedisConfiguration;
import com.chia.multienty.core.cache.redis.service.api.CommonRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@ConditionalOnClass(RedisConfiguration.class)
public class CommonRedisServiceImpl implements CommonRedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    private String LOCK_PREFIX="prefix";
    private long LOCK_EXPIRE=Long.parseLong("1000");

    @Override
    public boolean lock(String key) {
        String lock = LOCK_PREFIX + key;
        // 利用lambda表达式
        return (boolean) redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                long expireAt = System.currentTimeMillis() + LOCK_EXPIRE + 1;
                boolean acquire = redisConnection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
                if (acquire) {
                    return true;
                } else {
                    byte[] value = redisConnection.get(lock.getBytes());
                    if (Objects.nonNull(value) && value.length > 0) {
                        long expireTime = Long.parseLong(new String(value));
                        if (expireTime < System.currentTimeMillis()) {
                            // 如果锁已经过期
                            byte[] oldValue = redisConnection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                            // 防止死锁
                            return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                        }
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean expire(String key, long time) {
        try {
            if(time>0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Long getLong(String key) {
        Object val = redisTemplate.opsForValue().get(key);
        if(val == null) {
            return null;
        }
        return Long.parseLong(val.toString());
    }
    @Override
    public Integer getInteger(String key) {
        Object val = redisTemplate.opsForValue().get(key);
        if(val == null) {
            return null;
        }
        return Integer.parseInt(val.toString());
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        Object val = redisTemplate.opsForValue().get(key);
        if(val == null) {
            return null;
        }
        return new BigDecimal(val.toString());
    }

    @Override
    public long getExpire(String key) {
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    @Override
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void del(String... key) {
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
}
