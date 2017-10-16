package com.wz.mapper;

import com.wz.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 *
 * @author wangzi
 */
public interface OrderMapper {
    /**
     * 保存order
     * @param order
     * @return
     */
    Integer insert(@Param("order") Order order);

    /**
     * 查找全部order
     * @return
     */
    List<Order> findAll();

    /**
     * 根据id查找order
     * @param ids
     * @return
     */
    List<Order> findByIds(@Param("ids") List<Long> ids);
}
