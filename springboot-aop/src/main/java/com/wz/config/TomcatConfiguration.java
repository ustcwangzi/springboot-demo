package com.wz.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangzi on 2017-08-20.
 */
@Configuration
public class TomcatConfiguration {
    @Bean
    public EmbeddedServletContainerFactory servletContainerFactory(){
        TomcatEmbeddedServletContainerFactory containerFactory = new TomcatEmbeddedServletContainerFactory();
        containerFactory.setPort(9999);
        return containerFactory;
    }
}
