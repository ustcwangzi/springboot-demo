package com.wz.api;

import com.wz.domain.User;

/**
 * <p></p>
 *
 * @author wangzi
 */
public interface UserService {
    /**
     * 根据id查找user
     * @param id
     * @return
     */
    User queryById(int id);
}
