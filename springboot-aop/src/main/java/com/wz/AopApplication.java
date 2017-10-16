package com.wz;

import com.wz.service.TestService;
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
