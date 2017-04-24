package com.wz.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wz.api.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzi on 2017/4/20.
 */
@RestController
public class UserConsumerService {
    @Reference(version = "1.0.0")
    UserService userService;

    @RequestMapping("/hello")
    public String hello(){
        return userService.queryById(1).getName();
    }
}
