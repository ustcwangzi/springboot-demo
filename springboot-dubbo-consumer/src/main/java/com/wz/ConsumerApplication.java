package com.wz;

import com.wz.service.UserConsumerService;
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
public class ConsumerApplication {

    @Autowired
    private UserConsumerService userService;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @PostConstruct
    public void hello(){
        System.out.println(userService.hello());
    }
}
