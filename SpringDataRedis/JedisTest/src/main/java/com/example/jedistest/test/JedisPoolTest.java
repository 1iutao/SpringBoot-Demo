package com.example.jedistest.test;

import com.example.jedistest.JedisTestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JedisTestApplication.class)
public class JedisPoolTest {

    //日志工具
    private Logger logger = LoggerFactory.getLogger(JedisTest.class);

            @Test
    public void testJedisPool () {
                JedisPoolConfig config = new JedisPoolConfig();  //设置配置
                config.setMaxTotal(30); //配置最大连接数
                config.setMaxIdle(10);   //配置最大空闲连接数
                config.setMaxWaitMillis(10*1000);    //配置最大等待时间

                //初始化连接池
                JedisPool jedisPool = new JedisPool("localhost",6379);
                Jedis jedis = null;
                try {
                    //从连接池获取jedis核心对象
                    jedis = jedisPool.getResource();
                    //设置对象数据
                    jedis.set("name", "lt");
                    //获取数据
                    String str = jedis.get("name");
                    logger.warn("RedisPool测试："+ str);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    if (jedis != null) {
                        jedis.close();   //关闭连接释放资源
                    }
                }
                if (jedisPool != null) {
                    jedisPool.close();
                }
            }
}
