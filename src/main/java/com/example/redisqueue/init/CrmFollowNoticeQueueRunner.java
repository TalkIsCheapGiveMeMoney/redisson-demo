package com.example.redisqueue.init;

/**
 * Author:ZhuShangJin
 * Date:2018/9/13
 */



import com.example.redisqueue.utils.ThreadPoolUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.DelayQueue;

/**
 * Created by pangkunkun on 2017/9/3.
 */
@Component
@Order(3)
public class CrmFollowNoticeQueueRunner implements CommandLineRunner {
    @Autowired
    RedissonClient redissonClient;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    JedisPool jedisPool;


    @Override
    public void run(String... args) {
        CrmFollowNoticeHandler queueHandler = new CrmFollowNoticeHandler();
        queueHandler.setRedissonClient(redissonClient);
        queueHandler.setJedisPool(jedisPool);
        ThreadPoolUtils.fixedThreadPool.execute(queueHandler);

    }

}