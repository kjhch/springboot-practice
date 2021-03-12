/*
 * Cainiao.com Inc.
 * Copyright (c) 2013-2021 All Rights Reserved.
 */

package com.hch.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.hch.pojo.KafkaMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author hch
 * @since 2021-03-01
 */
@Service
@Validated
@Slf4j
public class HelloService {

    public void valid(@Valid @NotNull(message = "cannot be null") KafkaMsg msg) {
        log.info("{}", msg);
    }

    @Scheduled(cron = "1 * * * * ?")
    public void testSchedule() {
        System.out.println("hello");
    }
}