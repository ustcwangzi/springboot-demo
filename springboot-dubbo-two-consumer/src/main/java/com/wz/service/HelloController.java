package com.wz.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wz.api.IHelloservice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzi on 2017/4/21.
 */
@RestController
@RequestMapping("hello")
public class HelloController {
    @Reference(version="1.0.0")
    IHelloservice helloservice;
    @RequestMapping("/{name}")
    public String run(@PathVariable("name")String name){
        return helloservice.sayHello(name);
    }
}
