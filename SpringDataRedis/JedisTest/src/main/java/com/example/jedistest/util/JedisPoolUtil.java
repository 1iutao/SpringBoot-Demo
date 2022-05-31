package com.example.jedistest.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtil {
    //下面的这些配置属性可以根据需要修改。其实一般通过读取jedis.properties配置文件指定比较方便，这里只是作封装例子
    private static final String HOST = "132.232.6.208";
    private static final int PORT = 6379;
    private static final int MAX_TOTAL = 100;
    private static final int MAX_IDEL = 100;
    private static final int MAX_WAITMILLIS = 10 * 1000;
    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil() {
    }

    //获取jedis连接池单例模式对象，返回实例
    public static JedisPool getJedisPoolInstance()
    {
        if (jedisPool == null)
        {
            synchronized (JedisPoolUtil.class)
            {
                if (jedisPool == null) {

                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(MAX_TOTAL);           // 最大连接数
                    poolConfig.setMaxIdle(MAX_IDEL);              // 最大空闲连接数
                    poolConfig.setMaxWaitMillis(MAX_WAITMILLIS);  // 最大等待时间
                    poolConfig.setTestOnBorrow(true);       // 检查连接可用性, 确保获取的redis实例可用

                    jedisPool = new JedisPool(poolConfig, HOST, PORT);
                }
            }
        }

        return jedisPool;
    }

    //从连接池获取一个jedis连接
    public static Jedis getJedisInstance() {

        return getJedisPoolInstance().getResource();
    }

    //归还jedis连接
    public static void releaseJeids(Jedis jedis) {

        if (jedis != null) {
            jedis.close();     // jedisPool.returnResourceObject(jedis)已废弃
        }
    }


}