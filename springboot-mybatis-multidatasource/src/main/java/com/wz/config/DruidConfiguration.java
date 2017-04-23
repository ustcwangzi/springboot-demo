package com.wz.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by wangzi on 2017/4/19.
 */
@Configuration
public class DruidConfiguration {

    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource(
            @Value("${spring.datasource.primary.driver-class-name}") String driver,
            @Value("${spring.datasource.primary.url}") String url,
            @Value("${spring.datasource.primary.username}") String username,
            @Value("${spring.datasource.primary.password}") String password,
            @Value("${spring.datasource.primary.minIdle}") int minIdle,
            @Value("${spring.datasource.primary.maxActive}") int maxActive,
            @Value("${spring.datasource.primary.initialSize}") int initialSize,
            @Value("${spring.datasource.primary.timeBetweenEvictionRunsMillis}") long timeBetweenEvictionRunsMillis,
            @Value("${spring.datasource.primary.minEvictableIdleTimeMillis}") long minEvictableIdleTimeMillis,
            @Value("${spring.datasource.primary.validationQuery}") String validationQuery,
            @Value("${spring.datasource.primary.testWhileIdle}") boolean testWhileIdle,
            @Value("${spring.datasource.primary.testOnBorrow}") boolean testOnBorrow,
            @Value("${spring.datasource.primary.testOnReturn}") boolean testOnReturn){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        return druidDataSource;
    }

    @Bean(name = "secondaryDataSource")
    public DataSource secondaryDataSource(
            @Value("${spring.datasource.secondary.driver-class-name}") String driver,
            @Value("${spring.datasource.secondary.url}") String url,
            @Value("${spring.datasource.secondary.username}") String username,
            @Value("${spring.datasource.secondary.password}") String password,
            @Value("${spring.datasource.secondary.minIdle}") int minIdle,
            @Value("${spring.datasource.secondary.maxActive}") int maxActive,
            @Value("${spring.datasource.secondary.initialSize}") int initialSize,
            @Value("${spring.datasource.secondary.timeBetweenEvictionRunsMillis}") long timeBetweenEvictionRunsMillis,
            @Value("${spring.datasource.secondary.minEvictableIdleTimeMillis}") long minEvictableIdleTimeMillis,
            @Value("${spring.datasource.secondary.validationQuery}") String validationQuery,
            @Value("${spring.datasource.secondary.testWhileIdle}") boolean testWhileIdle,
            @Value("${spring.datasource.secondary.testOnBorrow}") boolean testOnBorrow,
            @Value("${spring.datasource.secondary.testOnReturn}") boolean testOnReturn){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnBorrow(testOnBorrow);
        druidDataSource.setTestOnReturn(testOnReturn);
        return druidDataSource;
    }
}
