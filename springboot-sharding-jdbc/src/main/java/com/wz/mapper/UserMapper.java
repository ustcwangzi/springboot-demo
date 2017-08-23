package com.wz.mapper;

import com.wz.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangzi on 2017-08-22.
 */
public interface UserMapper {
    Integer insert(@Param("user") User user);

    List<User> findAll();

    List<User> findByIds(@Param("ids") List<Long> ids);
}
