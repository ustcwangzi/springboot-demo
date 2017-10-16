package com.wz.service;

import com.wz.mapper.secondary.AddressMapper;
import com.wz.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Service
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;

    public Address queryById(int id){
        return addressMapper.queryByPrimaryKey(id);
    }

    public List<Address> queryByProvince(String province){
        return addressMapper.queryByProvince(province);
    }
}
