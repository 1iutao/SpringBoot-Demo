package com.example.redistest;

import com.example.redistest.entity.User;
import com.example.redistest.service.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisTestApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    void contextLoads() {
        System.out.println(redisTemplate);

        //redis有什么方法，jedis就有什么方法
        //redisTemplate的方法和Redis的命令是语义相近的，但是不一样
        redisTemplate.opsForValue().set("keys2", "value2");
        Object result = redisTemplate.opsForValue().get("keys2");
        System.out.println(result.toString());
    }

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testString() {
        for (int i = 0;i<10; i++) {
            String result = userService.getUserName("1002");
            System.out.println(result);
        }
    }

    @Test
    public void testHash() {
        for (int i = 0; i< 100; i++) {
            User user = userService.selectUserById("1002");
            System.out.println(user);
        }
    }


}
