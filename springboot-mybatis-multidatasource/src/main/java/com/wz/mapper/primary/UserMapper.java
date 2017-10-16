package com.wz.mapper.primary;

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
     * 根据id查找user
     * @param id
     * @return
     */
    User queryByPrimaryKey(@Param("id") int id);

    /**
     * 根据name查找user
     * @param name
     * @return
     */
    List<User> queryByUserName(@Param("name") String name);
}
