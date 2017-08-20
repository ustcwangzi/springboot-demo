package com.wz.service;

/**
 * Created by wangzi on 2017-08-20.
 */
public class CoreCustomService implements CustomService{
    @Override
    public void doSomething() {
        System.out.println("this is core service");
    }
}
