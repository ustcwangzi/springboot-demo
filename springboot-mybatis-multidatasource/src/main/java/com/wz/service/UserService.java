package com.wz.service;

import com.wz.mapper.primary.UserMapper;
import com.wz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryById(int id){
        return userMapper.queryByPrimaryKey(id);
    }

    public List<User> queryByUserName(String name){
        return userMapper.queryByUserName(name);
    }

}
