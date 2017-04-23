package com.wz.controller;

import com.wz.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangzi on 2017/4/23.
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/hello/{name}")
    public String doSomething(@PathVariable("name") String name){
        return testService.hello(name);
    }
}
