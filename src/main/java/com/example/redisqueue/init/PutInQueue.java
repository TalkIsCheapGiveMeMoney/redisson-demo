package com.example.redisqueue.init;

import com.example.redisqueue.entity.Trunk;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.util.concurrent.TimeUnit;

/**
 * Author:ZhuShangJin
 * Date:2018/11/2
 */
public class PutInQueue {
     public static void main(String args[]) {
         Config config = new Config();
         SingleServerConfig serverConfig = config.useSingleServer()
                 .setAddress("redis://192.168.19.173:6379")
                 .setConnectionPoolSize(50)
                 .setConnectionMinimumIdleSize(20);

         serverConfig.setPassword("ps666");


         RedissonClient redissonClient = Redisson.create(config);
         RList<Trunk> list = redissonClient.getList("anyList");

         RBlockingQueue<Trunk> blockingQueue = redissonClient.getBlockingQueue("queue");
         RDelayedQueue<Trunk> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
         for (int i = 0; i <20 ; i++) {

             Trunk trunk = new Trunk();
             trunk.setId(i+"");
             try {
                 blockingQueue.put(trunk);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
//             delayedQueue.offer(trunk,10, TimeUnit.SECONDS);
         }

         redissonClient.shutdown();

         }
}
