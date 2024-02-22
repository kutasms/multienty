package com.chia.multienty.core.cache.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class PrefixRedisSerializer implements RedisSerializer<String> {

    private final RedisSerializer<String> delegate = RedisSerializer.string();

    private final String prefix;

    public PrefixRedisSerializer(String prefix) {
        this.prefix = prefix;
    }


    @Override
    public byte[] serialize(String s) throws SerializationException {
        return delegate.serialize(prefix + s);
    }

    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return delegate.deserialize(bytes);
    }
}
