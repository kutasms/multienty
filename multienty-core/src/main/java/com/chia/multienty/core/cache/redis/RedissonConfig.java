package com.chia.multienty.core.cache.redis;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author ZhaoBW
 * @version 1.0
 * @date 2021/1/25 14:24
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private String database;

    @Value("${spring.redis.pingConnectionInterval}")
    private Integer pingConnectionInterval;

    /**
     * 所有对redisson的使用都是通过redissonclient对象
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        //1、创建配置
        // Redis url should start with redis:// or rediss:// (for SSL connection)
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);
        if (StringUtils.isNotEmpty(password)) {
            config.useSingleServer().setPassword(password);
        }
        config.useSingleServer().setDatabase(Integer.parseInt(database));
        config.useSingleServer().setPingConnectionInterval(pingConnectionInterval);
        config.useSingleServer().setConnectionMinimumIdleSize(3);
        //2、根据config创建出redissonClient实例
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
