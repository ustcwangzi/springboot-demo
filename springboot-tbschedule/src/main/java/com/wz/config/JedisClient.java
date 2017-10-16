package com.wz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Component
public class JedisClient {
    @Autowired
    private JedisPool jedisPool;

    public void set(String key, String value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        }finally {
            jedis.close();
        }
    }

    public void setex(String key, String value, int seconds){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.setex(key, seconds, value);
        }finally {
            jedis.close();
        }
    }

    public String get(String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }finally {
            jedis.close();
        }
    }
}
