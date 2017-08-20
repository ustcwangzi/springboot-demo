package com.wz.service;

/**
 * Created by wangzi on 2017-08-20.
 */
public class SimpleCustomService implements CustomService {
    @Override
    public void doSomething() {
        System.out.println("this is simple service");
    }
}
