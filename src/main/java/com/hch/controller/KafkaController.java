package com.hch.controller;

import com.hch.pojo.ErrorEnum;
import com.hch.pojo.KafkaMsg;
import com.hch.pojo.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hch
 * @since 2021/1/9
 */
@RestController
@Slf4j
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, KafkaMsg> kafkaTemplate;

    @PostMapping("/kafka/{topic}")
    public CommonResponse<?> sendMsg(@PathVariable String topic, @RequestBody List<KafkaMsg> msgs) {
        log.debug("controller: {}", msgs);
        for (KafkaMsg msg : msgs) {
            kafkaTemplate.send(topic, msg);
        }
        return new CommonResponse<>();
    }

}
