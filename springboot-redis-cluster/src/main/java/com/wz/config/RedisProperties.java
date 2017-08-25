package com.wz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wangzi on 2017/4/23.
 */
@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
    private String clusterNodes;
    private int maxWaitMillis;
    private int maxTotal;
    private int minIdle;
    private int maxIdle;
}
