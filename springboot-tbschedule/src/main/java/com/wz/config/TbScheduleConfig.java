package com.wz.config;

import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangzi on 2017-07-05.
 */
@Configuration
@EnableConfigurationProperties(TbScheduleProperties.class)
public class TbScheduleConfig {
    @Autowired
    private TbScheduleProperties properties;
    @Bean
    public TBScheduleManagerFactory tbScheduleManagerFactory() {
        TBScheduleManagerFactory factory = new TBScheduleManagerFactory();
        factory.setZkConfig(properties.getProperties());
        try {
            factory.init();
        } catch (Exception e) {
            System.err.println("TBSchedule init error:" + e);
        }
        return factory;
    }
}
