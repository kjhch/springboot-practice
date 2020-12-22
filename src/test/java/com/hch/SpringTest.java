package com.hch;

import com.hch.config.CustomProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private CustomProperties customProperties;


    @Test
    public void testPerson() {
        System.out.println(applicationContext.getBean("bean"));
        System.out.println(customProperties.getProjectName());
    }

    @Test
    public void testRedis() {
        // System.out.println(redisTemplate.getClientList());
        // System.out.println(redisTemplate.opsForValue().get("sbp:hello"));
    }
}
