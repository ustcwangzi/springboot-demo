package com.wz.service;

import com.wz.model.User;

import java.util.List;

/**
 * Created by wangzi on 2017-08-22.
 */
public interface UserService {
    Integer insert(User user);

    List<User> findByIds(List<Long> ids);
}
