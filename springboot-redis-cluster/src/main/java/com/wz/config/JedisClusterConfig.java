package com.wz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangzi on 2017/4/23.
 */
@Configuration
public class JedisClusterConfig {
    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
        return jedisPoolConfig;
    }

    @Bean
    public JedisCluster getJedisCluster(){
        String[] serverArray = redisProperties.getClusterNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for(String server:serverArray){
            String[] ipPortPair = server.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(nodes, getJedisPoolConfig());
    }

    @Bean
    public JedisConnectionFactory getJedisConnectionFactory(){
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        HashSet<RedisNode> nodes = new HashSet<>();
        String[] serverArray = redisProperties.getClusterNodes().split(",");
        for(String server:serverArray){
            String[] ipPortPair = server.split(":");
            RedisNode redisNode = new RedisNode(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim()));
            nodes.add(redisNode);
        }
        redisClusterConfiguration.setClusterNodes(nodes);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, getJedisPoolConfig());
        return jedisConnectionFactory;
    }
}
