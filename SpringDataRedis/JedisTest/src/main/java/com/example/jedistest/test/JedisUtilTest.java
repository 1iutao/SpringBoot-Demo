package com.example.jedistest.test;


import com.example.jedistest.util.JedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisUtilTest {
    @Test
    public void test() {
        Jedis jedis = null;
        try {
            jedis = JedisUtil.getJedis();   //从连接池获取jedis

            jedis.set("key1","value1");
            System.out.println(jedis.get("key1"));
        }
        finally {
            JedisUtil.close(jedis);     //归还jedis
        }
    }
}
