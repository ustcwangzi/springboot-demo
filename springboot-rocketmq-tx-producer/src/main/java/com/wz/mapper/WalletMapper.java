package com.wz.mapper;

import com.wz.model.Wallet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wangzi on 2017/6/19.
 */
@Mapper
public interface WalletMapper {
    Wallet queryById(@Param("userid") String userid);
    double queryAmountById(@Param("userid") String userid);
    int insert(@Param("wallet")Wallet wallet);
    int updateAmount(@Param("userid") String userid, @Param("amount") Double amount);
}
