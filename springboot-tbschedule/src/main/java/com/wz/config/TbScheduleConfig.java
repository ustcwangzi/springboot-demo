package com.wz.config;

import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Configuration
public class TbScheduleConfig {
    @Bean(initMethod = "init")
    public TBScheduleManagerFactory tbScheduleManagerFactory(
            @Value("${job.zkConfig.zkConnectString}")String zkConnectString,
            @Value("${job.zkConfig.rootPath}")String rootPath,
            @Value("${job.zkConfig.zkSessionTimeout}")String zkSessionTimeout,
            @Value("${job.zkConfig.userName}")String userName,
            @Value("${job.zkConfig.password}")String password,
            @Value("${job.zkConfig.isCheckParentPath}")String isCheckParentPath) {
        TBScheduleManagerFactory tbScheduleManagerFactory = new TBScheduleManagerFactory();
        Map<String, String> zkConfig = new HashMap<>(10);
        zkConfig.put("zkConnectString", zkConnectString);
        zkConfig.put("rootPath", rootPath);
        zkConfig.put("zkSessionTimeout", zkSessionTimeout);
        zkConfig.put("userName", userName);
        zkConfig.put("password", password);
        zkConfig.put("isCheckParentPath", isCheckParentPath);
        tbScheduleManagerFactory.setZkConfig(zkConfig);
        return tbScheduleManagerFactory;
    }
}
