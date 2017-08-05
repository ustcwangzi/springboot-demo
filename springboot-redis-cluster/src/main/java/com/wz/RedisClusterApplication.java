package com.wz;

import com.wz.service.TestCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * Created by wangzi on 2017/4/23.
 */
@SpringBootApplication
public class RedisClusterApplication {
    @Autowired
    private TestCache cache;

    public static void main(String[] args) {
        SpringApplication.run(RedisClusterApplication.class, args);
    }

    @PostConstruct
    public void test(){
        cache.add(1, "aaa");
        System.out.println(cache.query(1));
    }
}
