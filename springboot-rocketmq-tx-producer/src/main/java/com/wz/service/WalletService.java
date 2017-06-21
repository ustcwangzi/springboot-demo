package com.wz.service;

import com.wz.mapper.WalletMapper;
import com.wz.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangzi on 2017/6/19.
 */
@Service
@EnableTransactionManagement
@Transactional
public class WalletService {
    @Autowired
    private WalletMapper mapper;

    public Wallet findById(String userid){
        Wallet wallet = mapper.queryById(userid);
        System.out.println(wallet.toString());
        return wallet;
    }

    double queryAmountById(String userid){
        return mapper.queryAmountById(userid);
    }

    public int save(Wallet wallet) throws Exception{
        return mapper.insert(wallet);
    }

    public int updateAmount(String userid, double money) throws Exception{
        boolean isUpdate = false ;
        double amount = mapper.queryAmountById(userid);
        if(money > 0){
            amount = amount + money;
            isUpdate = true;
        }else if (money < 0){
            if (Math.abs(money) > amount){
                throw new Exception("余额不足");
            }else if(Math.abs(money) < amount){
                amount = amount - Math.abs(money);
                isUpdate = true;
            }
        }
        if(isUpdate){
            return mapper.updateAmount(userid, amount);
        }else {
            return 0;
        }
    }

    public int updateAmountWithException(String userid, double money) throws Exception{
        int result = updateAmount(userid, money);
        int ex = 10/0;
        return result;
    }
}
