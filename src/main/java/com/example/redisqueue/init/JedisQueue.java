package com.example.redisqueue.init;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Author:ZhuShangJin
 * Date:2018/11/2
 */
public class JedisQueue {
     public static void main(String args[]) {

         try {
             JedisPool jedisPool = new JedisPool(new URI("redis://:ps666@192.168.19.173:6379/1"));
             Jedis jedis = jedisPool.getResource();

             for (int i = 0; i < 5; i++) {
                 Calendar instance = Calendar.getInstance();
                 // 3秒后执行
                 instance.add(Calendar.SECOND, 10 + i);
                 //zadd key score  value
                 jedis.zadd("orderId", (instance.getTimeInMillis()) / 1000, StringUtils.join("000000000", i + 1));
                 System.out.println("生产订单: " + StringUtils.join("000000000", i + 1) + " 当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                 System.out.println((3 + i) + "秒后执行");
             }

             while (true) {
                 Set<Tuple> order = jedis.zrangeWithScores("orderId", 0, 0);
                 if (order == null || order.isEmpty()) {
                     System.out.println("当前没有等待的任务");
                     continue;
                 }
                 Tuple tuple = (Tuple) order.toArray()[0];
                 double score = tuple.getScore();
                 Calendar instance = Calendar.getInstance();
                 long nowTime = instance.getTimeInMillis() / 1000;
                 if (nowTime >= score) {
                     String element = tuple.getElement();
                     Long orderId = jedis.zrem("orderId", element);
                     if (orderId > 0) {
                         System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ":redis消费了一个任务：消费的订单OrderId为" + element);
                     }
                 }
             }
         } catch (URISyntaxException e) {
             e.printStackTrace();
         }





     }
}
