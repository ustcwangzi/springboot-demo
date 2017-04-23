package com.wz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;

/**
 * Created by wangzi on 2017/4/23.
 */
@Component
public class TestRedisCluster {
    @Autowired
    private JedisCluster jedisCluster;

    @PostConstruct
    public void run() {
        for (int i = 0; i < 100; i++) {
            //jedisCluster.set("redis"+i, "key"+i);
            String value= jedisCluster.get("redis"+i);
            System.err.println(value);
        }
    }
}
