package com.wz.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wz.api.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by wangzi on 2017/4/20.
 */
@Service
public class UserConsumerService {
    @Reference(version = "1.0.0")
    UserService userService;

    public String hello(){
        return userService.queryById(1).getName();
    }
}
