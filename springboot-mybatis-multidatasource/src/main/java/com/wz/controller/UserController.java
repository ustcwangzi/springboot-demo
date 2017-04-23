package com.wz.controller;

import com.wz.mapper.primary.UserMapper;
import com.wz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wangzi on 2017/4/19.
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/queryById")
    public User queryById(int id){
        return userMapper.queryByPrimaryKey(id);
    }

    @RequestMapping("/queryByUserName")
    public List<User> queryByUserName(String name){
        return userMapper.queryByUserName(name);
    }

}
