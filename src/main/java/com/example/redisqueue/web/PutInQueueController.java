package com.example.redisqueue.web;

import com.example.redisqueue.entity.Trunk;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Author:ZhuShangJin
 * Date:2018/11/8
 */
@RestController
@RequestMapping("/put")
public class PutInQueueController {
    @Autowired
    RedissonClient redissonClient;
    @RequestMapping("/order/{id}")
    public String put(@PathVariable("id") String id){
        Trunk trunk = new Trunk();
        trunk.setId(id);
        RBlockingQueue<Trunk> blockingQueue = redissonClient.getBlockingQueue("queue");
        RDelayedQueue<Trunk> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        delayedQueue.offer(trunk,20, TimeUnit.SECONDS);
        return  id;

    }
}
