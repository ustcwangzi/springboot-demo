package com.wz.mapper.primary;

import com.wz.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangzi on 2017/4/19.
 */
public interface UserMapper {
    User queryByPrimaryKey(@Param("id") int id);
    List<User> queryByUserName(@Param("name") String name);
}
