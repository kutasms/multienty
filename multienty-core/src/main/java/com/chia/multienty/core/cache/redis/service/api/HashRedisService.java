
package com.chia.multienty.core.cache.redis.service.api;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface HashRedisService {

    public Object hget(String key, String item);

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

    public double hdecr(String key, String item, double by);
}
