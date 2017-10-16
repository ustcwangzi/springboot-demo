package com.wz.service;

import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Service
public class TestService {
    public String hello(String name){
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Hello "+name;
    }
}
