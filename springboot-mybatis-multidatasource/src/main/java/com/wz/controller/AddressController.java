package com.wz.controller;

import com.wz.mapper.secondary.AddressMapper;
import com.wz.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wangzi on 2017/4/19.
 */
@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressMapper addressMapper;

    @RequestMapping("/queryById")
    public Address queryById(int id){
        return addressMapper.queryByPrimaryKey(id);
    }

    @RequestMapping("/queryByProvince")
    public List<Address> queryByProvince(String province){
        return addressMapper.queryByProvince(province);
    }
}
