package com.wz.mapper;

import com.wz.model.Bank;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wangzi on 2017/6/19.
 */
public interface BankMapper {
    Bank queryById(@Param("userid") String userid);
    int insert(@Param("bank")Bank bank);
    int updateAmount(@Param("userid") String userid, @Param("amount") Double amount);
}
