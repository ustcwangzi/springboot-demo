package com.wz.service;

import com.wz.model.User;

import java.util.List;

/**
 * <p></p>
 *
 * @author wangzi
 */
public interface UserService {
    /**
     * 保存user
     * @param user
     * @return
     */
    Integer insert(User user);

    /**
     * 根据id查找user
     * @param ids
     * @return
     */
    List<User> findByIds(List<Long> ids);
}
