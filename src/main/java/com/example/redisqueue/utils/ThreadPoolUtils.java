package com.example.redisqueue.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:ZhuShangJin
 * Date:2018/7/25
 */
public class ThreadPoolUtils {
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(25);
}
