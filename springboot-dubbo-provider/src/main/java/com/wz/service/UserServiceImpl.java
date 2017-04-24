package com.wz.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wz.api.UserService;
import com.wz.domain.User;

/**
 * Created by wangzi on 2017/4/20.
 */
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService{
    @Override
    public User queryById(int id) {
        return new User(1, "wang");
    }
}
