package com.wz;

import com.wz.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * Created by wangzi on 2017/4/23.
 */
@SpringBootApplication
public class AopApplication {
    @Autowired
    private TestService service;

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

    @PostConstruct
    public void test(){
        service.hello("AOP");
    }
}
