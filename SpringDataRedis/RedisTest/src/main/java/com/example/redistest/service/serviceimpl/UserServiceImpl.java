package com.example.redistest.service.serviceimpl;


import com.example.redistest.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl {

    @Autowired
    private RedisTemplate<String, Object>redisTemplate;

    //模拟操作String类型
    /*
    redis完成缓存功能（String类型操作）
    1.判断redis是否存在该key
    2.如果存在则直接查询
    3.如果不存在，查询mysql数据库，将查询的结果存入redis并返回
     */
    public String getUserName(String id) {
        //1.生成redis key
        String key = "user:name" + id;
        //2.判断是否存在key
        if (redisTemplate.hasKey(key)) {
            log.info("查询的是redis：");
            Object result = redisTemplate.opsForValue().get(key);
            return (String) result;
        }
        else {
            log.info("查询的是mysql：");
            String result = "lt";
            redisTemplate.opsForValue().set(key, result);
            return result;
        }

    }


    /*
    hash类型存取
     */
    public User selectUserById(String id) {
        //String key = "user+"+id;
        //判断key是否存在
        //hashOperation<k,hk,hv> k:user hk:id hv:user对象
        if (!redisTemplate.opsForHash().hasKey("user", id)) {
            log.info("查询的是mysql：");
            User u = new User();
            u.setId(id);
            u.setName("刘刘");
            u.setRemark("在学习redis");
            redisTemplate.opsForHash().put("user", id, u);
            return u;

        }
        else {
            log.info("查询的是redis：");
            Object obj = redisTemplate.opsForHash().get("user", id);
            return (User) obj;
        }
    }

}
