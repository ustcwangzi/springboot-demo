package com.wz.service;

import com.wz.model.Order;

import java.util.List;

/**
 * Created by wangzi on 2017-08-22.
 */
public interface OrderService {
    Integer insert(Order order);

    List<Order> findByIds(List<Long> ids);
}
