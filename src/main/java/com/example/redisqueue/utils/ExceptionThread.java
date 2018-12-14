package com.example.redisqueue.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:ZhuShangJin
 * Date:2018/12/14
 */

public class ExceptionThread implements Runnable {
    public String ss;

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }


    @Override
    public void run() {
        System.out.println("准备抛出异常");
        throw new RuntimeException(ss);
    }
    public static void main(String[] args) {
        //创建线程
        ExceptionThread exceptionThread = new ExceptionThread();
        exceptionThread.setSs("22");
        Thread thread = new Thread(exceptionThread);
        //创建线程池 指定线程池创建线程的 ThreadFactory 并设置线程名字
        ExecutorService service = Executors.newCachedThreadPool(new HandlerThreadFactory("专属线程名字"));
        service.execute(thread);
    }

}
