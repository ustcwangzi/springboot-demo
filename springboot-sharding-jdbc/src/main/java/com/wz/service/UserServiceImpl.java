package com.wz.service;

import com.wz.mapper.UserMapper;
import com.wz.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangzi on 2017-08-22.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper mapper;

    @Override
    public Integer insert(User user) {
        return mapper.insert(user);
    }

    @Override
    public List<User> findByIds(List<Long> ids) {
        return mapper.findByIds(ids);
    }
}
