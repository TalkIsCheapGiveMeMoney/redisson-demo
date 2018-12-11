package com.example.redisqueue.utils;

/**
 * Author:ZhuShangJin
 * Date:2018/12/11
 */
import java.util.ArrayList;
import java.util.List;

public class StringOomMock {
    static String  base = "string";
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i=0;i< Integer.MAX_VALUE;i++){
            String str = base + base;
            base = str;
            System.out.println(i);
            list.add(str.intern());
        }
    }
}