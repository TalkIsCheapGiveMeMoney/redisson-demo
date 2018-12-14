package com.example.redisqueue.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:ZhuShangJin
 * Date:2018/12/14
 */
public class HandlerThreadFactory implements ThreadFactory {
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    //线程名字前缀
    private final String namePrefix;

    public HandlerThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix+"-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread( r,namePrefix + threadNumber.getAndIncrement());
        System.out.println("创建一个新的线程");
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh121 = " + t.getUncaughtExceptionHandler());
        return t;
    }

}