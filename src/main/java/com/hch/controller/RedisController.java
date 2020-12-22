package com.hch.controller;

import com.hch.pojo.ErrorEnum;
import com.hch.pojo.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author hch
 * @since 2020/8/29
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private JedisPool jedisPool;

    @GetMapping("/hello")
    public CommonResponse<String> helloRedis(@RequestParam("value") String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            String author = jedis.get("author");
            return new CommonResponse<String>(ErrorEnum.SUCCESS).setData(author);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
