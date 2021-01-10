package com.hch.controller;

import com.hch.pojo.ErrorEnum;
import com.hch.pojo.response.CommonResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hch
 * @since 2021/1/9
 */
@RestController
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/kafka/send")
    public CommonResponse sendMsg(@RequestBody KafkaMsg msg) {
        kafkaTemplate.send(msg.getTopic(), msg.getMsg());
        return new CommonResponse(ErrorEnum.SUCCESS);
    }

    @Getter
    @Setter
    static class KafkaMsg {
        private String topic;
        private String msg;
    }
}
