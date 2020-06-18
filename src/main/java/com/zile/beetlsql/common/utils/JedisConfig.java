package com.zile.beetlsql.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

    @Autowired
    private Environment env;


    private String host;

    private int port;

    private int index;

    private String password;

    private int maxIdle;

    private long maxWait;

    private int minIdle;

    private int timeout;


    @Bean
    public JedisPool redisPoolFactory() {

        host = env.getProperty("spring.redis.host");
        port = Integer.parseInt(env.getProperty("spring.redis.port"));
        index = Integer.parseInt(env.getProperty("spring.redis.database"));
        password = env.getProperty("spring.redis.password");
        maxIdle = Integer.parseInt(env.getProperty("spring.redis.jedis.pool.max-idle"));
        maxWait = Integer.parseInt(env.getProperty("spring.redis.jedis.pool.max-wait"));
        minIdle = Integer.parseInt(env.getProperty("spring.redis.jedis.pool.min-idle"));
        timeout = Integer.parseInt(env.getProperty("spring.redis.timeout"));

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMinIdle(minIdle);


        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password, index);

        return jedisPool;
    }

}
