package com.hdz.socialplatform.initialization;

import org.junit.jupiter.api.Test;

import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * @author hdz
 * @description TODO
 * @create 2022年02月12日 14:10
 */

public class RedisTest {

    @Test
    public void start(){
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));

    }
}
