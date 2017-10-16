package com.wz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import javax.annotation.PostConstruct;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Component
public class TestRedisCluster {
    private static final int INIT_COUNT = 100;
    @Autowired
    private JedisCluster jedisCluster;

    @PostConstruct
    public void run() {
        for (int i = 0; i < INIT_COUNT; i++) {
            String value= jedisCluster.get("redis"+i);
            System.err.println(value);
        }
    }
}
