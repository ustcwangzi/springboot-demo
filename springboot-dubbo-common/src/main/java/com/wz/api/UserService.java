package com.wz.api;

import com.wz.domain.User;

/**
 * Created by wangzi on 2017/4/20.
 */
public interface UserService {
    User queryById(int id);
}
