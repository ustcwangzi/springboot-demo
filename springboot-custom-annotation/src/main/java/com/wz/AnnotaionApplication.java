package com.wz;

import com.wz.annotation.EnableCustomService;
import com.wz.service.CustomService;
import com.wz.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * <p></p>
 *
 * @author wangzi
 */
@SpringBootApplication
@EnableCustomService(Constants.POLICY_CORE)
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
