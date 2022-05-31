package com.example.jedistest.test;


import com.example.jedistest.JedisTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JedisTestApplication.class)
public class JedisTest {

    @Test
    public void testJedis() {

        //生成一个jedis对象，这个对象负责和指定redis节点进行通信
        Jedis jedis = new Jedis("localhost", 6379);

        //jedis存入数据
        jedis.set("hello", "world");

        //jedis获取数据
        String value = jedis.get("hello");

        Logger logger = LoggerFactory.getLogger(JedisTest.class);
        logger.warn("从Redis中存取数据："+value);
    }
}
