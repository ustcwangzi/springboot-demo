package com.wz.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wz.api.IHelloservice;
import org.springframework.stereotype.Component;

/**
 * Created by wangzi on 2017/4/21.
 */
@Component
@Service(version = "1.0.0")
public class HelloService implements IHelloservice {
    @Override
    public String sayHello(String name) {
        return "Hello "+name;
    }
}
