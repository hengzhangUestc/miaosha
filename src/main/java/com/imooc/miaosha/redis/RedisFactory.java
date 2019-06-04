package com.imooc.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisFactory {



    @Autowired
    private Redisconfig redisconfig;

    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig PoolConfig = new JedisPoolConfig();
        PoolConfig.setMaxIdle(redisconfig.getPoolMaxIdle());
        PoolConfig.setMaxTotal(redisconfig.getPoolMaxTotal());
        PoolConfig.setMaxWaitMillis(redisconfig.getPoolMaxWait() * 1000);
        JedisPool jp = new JedisPool(PoolConfig, redisconfig.getHost(), redisconfig.getPort(),
                redisconfig.getTimeout() * 1000);
        return jp;
    }
}
