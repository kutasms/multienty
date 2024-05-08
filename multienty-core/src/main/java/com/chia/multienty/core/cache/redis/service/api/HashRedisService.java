
package com.chia.multienty.core.cache.redis.service.api;

import java.util.List;
import java.util.Map;

public interface HashRedisService {


    Object hget(Object key, String item);

    <HK, HV> List<HV> getMulti(HK key, String... items);

    public Map<String, Object> hgetAll(String key);

    public <T> T hgetAll(String key, Class<T> clazz);

    public Map<Object, Object> hmget(String key);

    public boolean hmset(String key, Map<String, Object> map);

    public boolean hmset(String key, Map<String, Object> map, long time);

    public boolean hset(String key, String item, Object value);

    public boolean hset(String key, String item, Object value, long time);

    public void hdel(String key, Object... item);

    public boolean hHasKey(String key, String item);

    public double hincr(String key, String item, double by);

    long incr(String key, String item, long val);

    long decr(String key, String item, long val);

    public double hdecr(String key, String item, double by);
}
