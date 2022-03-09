package com.docker.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DemoController {
    @Autowired
    RedisTemplate redisTemplate;
    public static final String template = "Hello Docker name:%s with id:%s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/getUser")
    public ResponseEntity<String> helloDocker(@RequestParam(value="name",defaultValue = "World") String name){
        Object response= redisTemplate.opsForHash().get("USER",name);
        return new ResponseEntity<>("Hello Docker name:"+name +" redis reponse:"+ response, HttpStatus.OK);
    }

    @GetMapping("/addUser")
    public ResponseEntity<String> addValue(@RequestParam(value = "name") String name){
        String id= String.valueOf(counter.incrementAndGet());
        redisTemplate.opsForHash().put("USER",name,id);
        return new ResponseEntity<>("Add name:"+name +" with id:"+id +"success", HttpStatus.OK);
    }
}
