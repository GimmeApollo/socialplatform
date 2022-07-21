package com.hdz.socialplatform.initialization;

import com.hdz.socialplatform.SocialPlatformApplicationTests;
import com.hdz.socialplatform.entity.Event;
import com.hdz.socialplatform.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author hdz
 * @description TODO
 * @create 2022年02月12日 17:29
 */

@Slf4j
public class RedisTemplateTest extends SocialPlatformApplicationTests {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;   //用Object还是Serializable好？

    @Resource
    private RedisTemplate<String, Event> eventRedisTemplate;   //用Object还是Serializable好？

    /**
     * 测试 Redis 操作
     */
    @Test
    public void get() {
        // 1、测试线程安全，程序结束查看redis中count的值是否为1000
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("count", 1)));

        //2、测试string
        stringRedisTemplate.opsForValue().set("k1", "v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.debug("【k1】= {}", k1);

        //3、测试object
        String key = "hdz:user:1";
        redisTemplate.opsForValue().set(key, new User());
        // 对应 String（字符串）
        User user = (User) redisTemplate.opsForValue().get(key);
        log.debug("【user】= {}", user);

        //4、测试带泛型的list
        String keyList = "hdz:userList";
        List<User> users= Lists.newArrayList(user);
        redisTemplate.opsForValue().set(keyList,users,5, TimeUnit.MINUTES);
        List<User> list2 = (List<User>)redisTemplate.opsForValue().get(keyList);
        list2.forEach(user1 -> System.out.println(user1.toString()));

        //5、测试hash
        String hashKey = "hdz:userMap";
        HashMap<String, User> hashMap = new HashMap<>(){
            {
                this.put("hdz:user:1",new User());
                this.put("hdz:user:2",new User());
                this.put("hdz:user:3",new User());
            }
        };
        redisTemplate.boundHashOps(hashKey).putAll(hashMap);
        String key1="hdz:user:3";
        User user1 = (User) redisTemplate.boundHashOps(hashKey).get(key1);
        log.debug("hashKey{"+hashKey+"}中key1{"+key1+"}对应的User为"+user1.toString());
        System.out.println(eventRedisTemplate);
    }

}
