/*
 * Cainiao.com Inc.
 * Copyright (c) 2013-2021 All Rights Reserved.
 */

package com.hch.service;

import com.hch.pojo.KafkaMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author hch
 * @since 2021-03-04 10:03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    public void testValid() throws Exception {
        helloService.valid(new KafkaMsg().setAge(1));
        helloService.valid(null);
    }
}