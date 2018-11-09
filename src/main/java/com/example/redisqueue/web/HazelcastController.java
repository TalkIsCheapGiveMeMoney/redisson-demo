package com.example.redisqueue.web;

import com.example.redisqueue.entity.Trunk;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * Author:ZhuShangJin
 * Date:2018/11/9
 */

@RestController
@RequestMapping("/hazelcast")
public class HazelcastController {

    private final Logger logger = LoggerFactory.getLogger(HazelcastController.class);
    @Autowired
    HazelcastInstance hazelcastInstance;



    @RequestMapping(value = "/write/{key}/{value}")
    public String writeDataToHazelcast(@PathVariable("key") String key, @PathVariable("value") String value) {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        hazelcastMap.put(key, value);
        return "Data is stored.";
    }

    @RequestMapping(value = "/read/{key}")
    public String readDataFromHazelcast(@PathVariable("key") String key) {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        return hazelcastMap.get(key);
    }

    @GetMapping(value = "/readAll")
    public Map<String, String> readAllDataFromHazelcast() {
        Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
        BlockingQueue<Trunk> ee = hazelcastInstance.getQueue("11");
        return hazelcastInstance.getMap("my-map");
    }

}

