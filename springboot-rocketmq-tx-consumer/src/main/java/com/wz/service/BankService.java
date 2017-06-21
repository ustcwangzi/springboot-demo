package com.wz.service;

import com.wz.mapper.BankMapper;
import com.wz.model.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangzi on 2017/6/19.
 */
@Service
public class BankService {
    @Autowired
    private BankMapper mapper;

    public Bank findById(String id){
        Bank bank = mapper.queryById(id);
        System.out.println(bank.toString());
        return bank;
    }

    public int save(Bank bank){
        return mapper.insert(bank);
    }

    public int updateAmount(Bank bank){
        return mapper.updateAmount(bank.getUserid(), bank.getAmount());
    }
}
