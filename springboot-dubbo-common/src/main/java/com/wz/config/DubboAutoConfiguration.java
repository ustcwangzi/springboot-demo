package com.wz.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangzi on 2017/4/20.
 */
@Configuration
@EnableConfigurationProperties({DubboProperties.class})
public class DubboAutoConfiguration {
    @Autowired
    private DubboProperties dubboProperties;

    public DubboAutoConfiguration() {
    }

    @Bean
    public ApplicationConfig requestApplicationConfig() {
        return this.dubboProperties.getApplication();
    }

    @Bean
    public RegistryConfig requestRegistryConfig() {
        return this.dubboProperties.getRegistry();
    }

    @Bean
    public ProtocolConfig requestProtocolConfig() {
        return this.dubboProperties.getProtocol();
    }
}
