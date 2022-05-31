package com.example.jedistest.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

public final class JedisUtil {
    //从配置文件jedis.properties读取Jedis连接池配置生成连接池，然后从连接池获取jedis
    private static String host;
    private static int port;
    private static int maxtotal;
    private static int maxidel;
    private static int maxwaitmillis;
    private JedisUtil() {}
    private static volatile JedisPool jedisPool = null;

    static {
        //静态代码用于类初始化，在虚拟机加载类的时候执行一次，并且在main函数之前执行
        //读取jedis.properties配置文件
        ResourceBundle rb = ResourceBundle.getBundle("jedis");
        host = rb.getString("host");
        port = Integer.parseInt(rb.getString("port"));
        maxtotal = Integer.parseInt(rb.getString("maxtotal"));
        maxidel = Integer.parseInt(rb.getString("maxidel"));
        maxwaitmillis = Integer.parseInt(rb.getString("maxwaitmillis"));

    }

    static {
        //创建单例连接池
        if (jedisPool == null) {
            synchronized (JedisPoolUtil.class) {
                if (jedisPool == null) {
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(maxtotal);
                    poolConfig.setMaxIdle(maxidel);
                    poolConfig.setMaxWaitMillis(maxwaitmillis);
                    poolConfig.setTestOnBorrow(true);   //检查连接可用性

                    jedisPool = new JedisPool(poolConfig, host, port);
                }
            }
        }
    }

    public static Jedis getJedis() {
        //获取jedis
        return jedisPool.getResource();
    }


    public static void close(Jedis jedis) {
        //归还jedis
        if (jedis != null) {
            jedis.close();
        }
    }
}

