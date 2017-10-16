package com.wz.config;

import lombok.Data;

/**
 * <p></p>
 *
 * @author wangzi
 */
@Data
public class MyBatisProperties {
    private String name;
    private String basePackage;
    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;
    private Boolean readOnly;
    private Long connectionTimeout;
    private Long idleTimeout;
    private Long maxLifetime;
    private Integer maximumPoolSize;
    private String mapperLocations;
    private String typeAliasesPackage;
    private String configLocation;
    private Integer minimumIdle;
    private String dataSourceClassName;
    private String validationQuery;
}
