package com.wz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>加载配置，并根据运行环境获取配置</p>
 * <p>运行时通过 -Denv=dev 指定运行环境</p>
 * Created by wangzi on 2017-08-21.
 */
public class EnvironmentManager {
    private static Logger logger = LoggerFactory.getLogger(EnvironmentManager.class);
    private static final String APP_PROPERTIES_ENV_PATH = "classpath*:META-INF/env-*.properties";
    private static final String APP_PROPERTIES_ENV_PATH_SUFFIX = ".properties";
    private static final Map<String, Properties> PROPERTIES_MAP = new HashMap<>();
    private static Properties properties;

    public static final String SHARDING_DEFATULT_DS_INDEX = "sharding.defaultDSIndex";
    public static final String SHARDING_MAPPERLOCATIONS = "sharding.mapperLocations";
    public static final String SHARDING_TYPEALIASESPACKAGE = "sharding.typeAliasesPackage";
    public static final String SHARDING_DATASOURCES_DRIVERCLASSNAME = "sharding.dataSources[%s].driverClassName";
    public static final String SHARDING_DATASOURCES_JDBCURL = "sharding.dataSources[%s].jdbcUrl";
    public static final String SHARDING_DATASOURCES_USERNAME = "sharding.dataSources[%s].username";
    public static final String SHARDING_DATASOURCES_PASSWORD = "sharding.dataSources[%s].password";
    public static final String SHARDING_DATASOURCES_READONLY = "sharding.dataSources[%s].readOnly";
    public static final String SHARDING_DATASOURCES_CONNECTIONTIMEOUT = "sharding.dataSources[%s].connectionTimeout";
    public static final String SHARDING_DATASOURCES_IDLETIMEOUT = "sharding.dataSources[%s].idleTimeout";
    public static final String SHARDING_DATASOURCES_MAXLIFETIME = "sharding.dataSources[%s].maxLifetime";
    public static final String SHARDING_DATASOURCES_MAXIMUMPOOSIZE = "sharding.dataSources[%s].maximumPoolSize";
    public static final String SHARDING_DATASOURCES_MINIMUMIDLE = "sharding.dataSources[%s].minimumIdle";
    public static final String SHARDING_DATASOURCES_DATASOURCECLASSNAME = "sharding.dataSources[%s].dataSourceClassName";
    public static final String SHARDING_DATASOURCES_VALIDATIONQUERY = "sharding.dataSources[%s].validationQuery";
    public static final String SHARDING_TABLECONFIGS_NAME = "sharding.tableConfigs[%s].name";
    public static final String SHARDING_TABLECONFIGS_CLOUMN = "sharding.tableConfigs[%s].shardingColumn";
    public static final String SHARDING_TABLECONFIGS_SIZE = "sharding.tableConfigs[%s].size";
    public static final String SHARDING_TABLECONFIGS_FORMAT = "sharding.tableConfigs[%s].format";

    static {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = resolver.getResources(APP_PROPERTIES_ENV_PATH);
            if (resources != null && resources.length > 0) {
                for (Resource resource : resources) {
                    Properties props = new Properties();
                    props.load(resource.getInputStream());
                    PROPERTIES_MAP.put(getEnvPropertyFileName(resource), props);
                }
            }
        } catch (IOException e) {
            logger.error("Load resource error: {}", e.getMessage());
        }
    }

    private static String getEnvPropertyFileName(Resource resource) {
        return resource.getFilename().substring(4, resource.getFilename().indexOf(APP_PROPERTIES_ENV_PATH_SUFFIX));
    }

    public static String getEnv() {
        final String env = System.getProperty("env");
        return env == null ? "dev" : env;
    }

    public static String getProperty(String key) {
        if (properties == null) {
            properties = PROPERTIES_MAP.get(getEnv());
            if (properties == null) {
                properties = new Properties();
            }
        }
        return properties.getProperty(key);
    }
}
