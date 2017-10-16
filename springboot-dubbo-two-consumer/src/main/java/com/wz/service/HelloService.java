package com.wz.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wz.api.IHelloservice;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Service
public class HelloService {
    @Reference(version="1.0.0", timeout = 3000)
    IHelloservice helloservice;

    public String hello(String name){
        return helloservice.sayHello(name);
    }
}
