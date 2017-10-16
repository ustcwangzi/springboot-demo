package com.wz.service;

import com.wz.mapper.OrderMapper;
import com.wz.model.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper mapper;

    @Override
    public Integer insert(Order order) {
        return mapper.insert(order);
    }

    @Override
    public List<Order> findByIds(List<Long> ids) {
        return mapper.findByIds(ids);
    }
}
