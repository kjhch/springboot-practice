package com.hch.controller;

import com.hch.pojo.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author hch
 * @since 2020/8/29
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/hello")
    public CommonResponse<String> helloRedis(@RequestParam("value")String value){
        redisTemplate.opsForValue().get("");
        redisTemplate.opsForValue().set("sbp:hello", value, 100, TimeUnit.SECONDS);
        return new CommonResponse<>();
    }
}
