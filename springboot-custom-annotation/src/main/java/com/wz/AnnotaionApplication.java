package com.wz;

import com.wz.annotation.EnableCustomService;
import com.wz.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * Created by wangzi on 2017-08-20.
 */
@SpringBootApplication
@EnableCustomService("core")
public class AnnotaionApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnnotaionApplication.class, args);
    }

    @Autowired
    private CustomService service;

    @PostConstruct
    public void run(){
        service.doSomething();
    }
}
