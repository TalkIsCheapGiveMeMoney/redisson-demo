package com.example.redisqueue.init;

import com.example.redisqueue.entity.Trunk;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Author:ZhuShangJin
 * Date:2018/10/25
 */

public class CrmFollowNoticeHandler implements  Runnable {
    RedissonClient redissonClient;
    JedisPool jedisPool;

    @Override
    public void run() {
////        RBlockingQueue<Trunk> blockingQueue = redissonClient.getBlockingQueue("queue");
//
//        while (true){
//            Trunk trunk = null;
//            try {
//                trunk = blockingQueue.take();
//                System.out.println(trunk.getId());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        while (true){
            List<String> result = jedisPool.getResource().blpop(Integer.MAX_VALUE,"queue");
            if (result != null && result.size() > 1) {
                String key = result.get(1);
                System.out.println(key);

            }
        }


    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
