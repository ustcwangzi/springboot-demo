package com.wz.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.wz.api.IHelloservice;

/**
 * Created by wangzi on 2017/4/21.
 */
@Service
public class HelloService {
    @Reference(version="1.0.0")
    IHelloservice helloservice;

    public String hello(String name){
        return helloservice.sayHello(name);
    }
}
