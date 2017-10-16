package com.wz.service;

import com.wz.model.Order;

import java.util.List;

/**
 * <p></p>
 *
 * @author wangzi
 */
public interface OrderService {
    /**
     * 保存order
     * @param order
     * @return
     */
    Integer insert(Order order);

    /**
     * 根据id查找order
     * @param ids
     * @return
     */
    List<Order> findByIds(List<Long> ids);
}
