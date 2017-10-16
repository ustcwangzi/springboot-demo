package com.wz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Service
@EnableCaching
public class TestCache {
    @Autowired
    private CacheService cacheService;

    public String query(int id){
        return "["+getDateNow()+"]:"+cacheService.query(id);
    }

    public String add(int id, String value){
        return "["+getDateNow()+"]:"+cacheService.add(id ,value);
    }

    public String delete(int id){
        cacheService.delete(id);
        return "["+getDateNow()+"]:"+id+" delete success";
    }

    private static String getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
