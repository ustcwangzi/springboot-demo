package com.wz.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
 * <p>封装redis的操作，防止缓存穿透</p>
 *
 * @author wangzi
 */
@Component
public class RedisClient {
    @Autowired
    private JedisCluster jedis;

    public <T> T findCache(String key, int expireSeconds, Class<T> clazz, LoadCallBack<T> callBack){
        String json = jedis.get(key);
        if (json == null || json.length() == 0){
            synchronized (this){
                json = jedis.get(key);
                if (json == null || json.length() == 0){
                    T t = callBack.load();
                    if (expireSeconds > 0){
                        jedis.setex(key, expireSeconds, JSON.toJSONString(t));
                    }else {
                        jedis.set(key, JSON.toJSONString(t));
                    }
                    return t;
                }
                return JSON.parseObject(json, clazz);
            }
        }else {
            return JSON.parseObject(json, clazz);
        }
    }
}
