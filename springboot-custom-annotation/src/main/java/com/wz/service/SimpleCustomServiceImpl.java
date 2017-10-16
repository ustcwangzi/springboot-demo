package com.wz.service;

/**
 * <p></p>
 *
 * @author wangzi
 */
public class SimpleCustomServiceImpl implements CustomService {
    @Override
    public void doSomething() {
        System.out.println("this is simple service");
    }
}
