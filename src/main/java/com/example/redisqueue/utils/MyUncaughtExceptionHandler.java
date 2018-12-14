package com.example.redisqueue.utils;

/**
 * Author:ZhuShangJin
 * Date:2018/12/14
 */

public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("线程名字===="+t.getName());
        System.out.println("捕获异常" + e.toString());
    }

}