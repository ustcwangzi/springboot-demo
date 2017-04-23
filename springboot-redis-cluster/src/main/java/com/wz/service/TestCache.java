package com.wz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangzi on 2017/4/23.
 */
@RestController
@EnableCaching
public class TestCache {
    @Autowired
    private CacheService cacheService;

    @RequestMapping("get")
    public String query(int id){
        return "["+getDateNow()+"]:"+cacheService.query(id);
    }

    @RequestMapping("put")
    public String add(int id, String value){
        return "["+getDateNow()+"]:"+cacheService.add(id ,value);
    }

    @RequestMapping("del")
    public String delete(int id){
        cacheService.delete(id);
        return "["+getDateNow()+"]:"+id+" delete success";
    }

    private static String getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
