package com.example.redis.springbootredismessagingdemo.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sender {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("/publish")
    public void publish(@RequestParam("message") String message){
        stringRedisTemplate.convertAndSend("chat",message);

    }
}
