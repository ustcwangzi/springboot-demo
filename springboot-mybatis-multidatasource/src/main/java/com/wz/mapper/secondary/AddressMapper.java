package com.wz.mapper.secondary;

import com.wz.model.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 *
 * @author wangzi
 */
public interface AddressMapper {
    /**
     * 根据id查找地址
     * @param id
     * @return
     */
    Address queryByPrimaryKey(@Param("id") int id);

    /**
     * 根据省份查找地址
     * @param province
     * @return
     */
    List<Address> queryByProvince(@Param("province") String province);
}
