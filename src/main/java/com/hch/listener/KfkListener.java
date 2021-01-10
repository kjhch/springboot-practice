package com.hch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author hch
 * @since 2021/1/9
 */
@Component
@Slf4j
public class KfkListener {

    @KafkaListener(topics = "mytopic", groupId = "spring")
    public void listenMyTopic(String msg) {
        log.debug(msg);
    }
}
