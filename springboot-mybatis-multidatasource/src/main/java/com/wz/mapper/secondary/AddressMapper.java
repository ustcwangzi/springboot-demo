package com.wz.mapper.secondary;

import com.wz.model.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangzi on 2017/4/19.
 */
public interface AddressMapper {
    Address queryByPrimaryKey(@Param("id") int id);
    List<Address> queryByProvince(@Param("province") String province);
}
