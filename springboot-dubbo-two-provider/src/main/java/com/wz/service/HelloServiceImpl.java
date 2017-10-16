package com.wz.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wz.api.IHelloservice;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Component
@Service(version = "1.0.0", timeout = 3000)
public class HelloServiceImpl implements IHelloservice {
    @Override
    public String sayHello(String name) {
        try{
            Thread.sleep(2000);
        }catch (Exception e){}
        return "Hello "+name;
    }
}
