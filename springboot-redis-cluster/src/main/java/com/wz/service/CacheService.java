package com.wz.service;

import com.wz.util.LoadCallBack;
import com.wz.util.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzi on 2017/4/23.
 */
@Service
public class CacheService {
    private static final Logger logger = LoggerFactory.getLogger(CacheService.class);
    //用来模拟对数据库的操作
    private Map<Integer, String> dataMap = new HashMap<>();
    private Integer count = 0;
    @Autowired
    private RedisClient client;

    @PostConstruct
    public void init(){
        for(int i=1; i<4; i++){
            dataMap.put(i, "value"+i);
        }
    }

    /**
     * 查询
     * 如果数据没有缓存,那么从dataMap里面获取
     * 如果缓存了,那么从RedisCache里面获取
     * 并且将缓存的数据存入到 RedisCache里面
     * 其中key 为 dataMap_ + #id
     */
    @Cacheable(value = "RedisCache", key = "'dataMap_' + #id")
    public String query(int id){
        System.out.println("["+getDateNow()+"]:"+id);
        return dataMap.get(id);
    }

    /**
     * 插入或更新数据到dataMap中
     * 并且缓存到 RedisCache中
     * 如果存在了那么更新缓存中的值
     * 其中key 为 dataMap_ + #id
     */
    @CachePut(value = "RedisCache", key = "'dataMap_' + #id")
    public String add(int id, String value){
        System.out.println("["+getDateNow()+"]:"+id);
        dataMap.put(id, value);
        return value;
    }

    /**
     * 删除dataMap里面的数据
     * 并且删除缓存RedisCache中的数据
     * 其中key 为 dataMap_ + #id
     */
    @CacheEvict(value = "RedisCache", key = "'dataMap_' + #id")
    public void delete(int id){
        System.out.println("["+getDateNow()+"]:"+id);
        dataMap.remove(id);
    }

    private static String getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public String query(){
        String key = "wangzi-key";
        return client.findCache(key, 3, String.class, new LoadCallBack<String>() {
            @Override
            public String load() {
                logger.info("-----------no cache-----------");
                try{
                    //模拟查询数据库
                    Thread.sleep(300);
                    //记录查询数据库的次数
                    count++;
                    return "wangzi-value";
                }catch (InterruptedException e){
                    logger.error(e.getMessage());
                }
                return null;
            }
        });
    }

    public Integer getCount() {
        return count;
    }
}
