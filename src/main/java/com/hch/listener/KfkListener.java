package com.hch.listener;

import com.hch.pojo.KafkaMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author hch
 * @since 2021/1/9
 */
@ConditionalOnProperty("spring.kafka.consumer.bootstrap-servers")
@Component
@Slf4j
public class KfkListener {

    @KafkaListener(topics = "mytopic", groupId = "spring")
    public void listenMyTopic(KafkaMsg msg, Acknowledgment ack) {
        log.debug(msg.toString());
        ack.acknowledge();
    }
}
