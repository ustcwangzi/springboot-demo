package com.wz.mapper;

import com.wz.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 *
 * @author wangzi
 */
public interface UserMapper {
    /**
     * 保存user
     * @param user
     * @return
     */
    Integer insert(@Param("user") User user);

    /**
     * 查找所有user
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查找user
     * @param ids
     * @return
     */
    List<User> findByIds(@Param("ids") List<Long> ids);
}
