package com.hch.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@MapperScan("com.hch.dao")  // 扫描标注了Mapper注解的类
public class DBConfig {
    @Value("${redis.server}")
    private String redisServer;

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);
        poolConfig.setMinIdle(5);
        poolConfig.setMaxWaitMillis(500);
        String[] hostAndPort = redisServer.split(":");
        return new JedisPool(poolConfig, hostAndPort[0], Integer.parseInt(hostAndPort[1]));
    }
}
