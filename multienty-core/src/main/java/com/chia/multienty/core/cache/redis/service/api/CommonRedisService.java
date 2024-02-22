
package com.chia.multienty.core.cache.redis.service.api;

import java.math.BigDecimal;

public interface CommonRedisService {

    public boolean lock(String key);

    public void delete(String key);

    public boolean expire(String key, long time);

    Long getLong(String key);

    Integer getInteger(String key);

    BigDecimal getBigDecimal(String key);

    public long getExpire(String key);

    public boolean hasKey(String key);

    public void del(String... key);
}
