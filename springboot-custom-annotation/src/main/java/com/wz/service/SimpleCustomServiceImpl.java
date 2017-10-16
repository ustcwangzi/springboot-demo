package com.wz.service;

/**
 * @author wangzi
 * Created by wangzi on 2017-08-20.
 */
public class SimpleCustomServiceImpl implements CustomService {
    @Override
    public void doSomething() {
        System.out.println("this is simple service");
    }
}
