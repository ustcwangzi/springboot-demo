package com.wz.model;

import java.util.Date;

/**
 * Created by wangzi on 2017/6/19.
 */
public class Wallet {
    private String userid;
    private String username;
    private double amount;
    private Date updateTime;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                ", updateTime=" + updateTime +
                '}';
    }
}
