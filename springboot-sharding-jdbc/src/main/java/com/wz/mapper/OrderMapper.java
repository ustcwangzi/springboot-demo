package com.wz.mapper;

import com.wz.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangzi on 2017-08-22.
 */
public interface OrderMapper {
    Integer insert(@Param("order") Order order);

    List<Order> findAll();

    List<Order> findByIds(@Param("ids") List<Long> ids);
}
