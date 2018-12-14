package com.example.redisqueue.web;

/**
 * Author:ZhuShangJin
 * Date:2018/12/14
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable {

    @Override
    public void run() {
        System.out.println(111);
        throw new RuntimeException("这个线程就干了这么一件事，抛出一个运行时异常");
    }

    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
            System.out.println("该干嘛干嘛去");
        } catch (RuntimeException e) {
            System.out.println("能不能捕获到异常？");
        }

    }

}
