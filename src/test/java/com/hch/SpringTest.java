package com.hch;

import com.hch.config.CustomProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private CustomProperties customProperties;
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Test
    public void testPerson() {
        System.out.println(applicationContext.getBean("bean"));
        System.out.println(customProperties.getProjectName());
    }

    @Test
    public void testRedis(){
        // System.out.println(redisTemplate.getClientList());
        // System.out.println(redisTemplate.opsForValue().get("sbp:hello"));
    }
}
