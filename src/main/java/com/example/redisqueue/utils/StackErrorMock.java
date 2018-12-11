package com.example.redisqueue.utils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:ZhuShangJin
 * Date:2018/12/10
 */
public class StackErrorMock {
    private static int index = 1;

    public void call(){
        index++;
        call();
    }

    public static void main(String[] args) {
        StackErrorMock mock = new StackErrorMock();
        try {
            mock.call();
        }catch (Throwable e){
            System.out.println("Stack deep : "+index);
            e.printStackTrace();
        }
    }
}
