
package com.chia.multienty.core.cache.redis.service.api;

import java.util.Set;

public interface SetRedisService {

    public Set<Object> sGet(String key);

    public boolean sHasKey(String key, Object value);

    public long sSet(String key, Object... values);

    public long sSetAndTime(String key, long time, Object... values);

    public long sGetSetSize(String key);

    public long setRemove(String key, Object... values);
}
