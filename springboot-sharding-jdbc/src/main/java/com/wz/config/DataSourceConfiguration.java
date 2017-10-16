package com.wz.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.*;

/**
 * <p>构建Sharding数据源</p>
 *
 * @author wangzi
 */
@Slf4j
@Configuration
@MapperScan(basePackages = {"com.wz.mapper"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
public class DataSourceConfiguration {
    private Map<String, DataSource> dataSourceMap;
    private String defaultDataSource;
    private List<TableConfig> tableConfigs;
    private String mapperLocations;
    private String typeAliasesPackage;

    @Bean("shardingDataSource")
    public DataSource dataSource() {
        initConfig();
        return buildShardingDataSource();
    }

    @Bean
    @ConditionalOnBean(DataSource.class)
    public SqlSessionFactoryBean factoryBean(){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setMapperLocations(ResourceHelp.resolveMapperLocations(mapperLocations));
        factoryBean.setTypeAliasesPackage(typeAliasesPackage);
        factoryBean.setDataSource(dataSource());
        return factoryBean;
    }

    @Bean
    @ConditionalOnBean(DataSource.class)
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    @ConditionalOnBean(DataSourceTransactionManager.class)
    public TransactionTemplate transactionTemplate(){
        return new TransactionTemplate(transactionManager());
    }

    /**
     * <p>构建Sharding数据源</p>
     */
    public DataSource buildShardingDataSource(){
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, defaultDataSource);
        List<TableRule> tableRuleList = new ArrayList<>(tableConfigs.size());
        for (TableConfig config : tableConfigs){
            List<String> tableNames = ShardingJbdcUtil.generationTableNames(config.getName(), config.getSize(),config.getFormat());
            SimpleShardingAlgorithm simpleShardingAlgorithm = new SimpleShardingAlgorithm(config);
            TableRule tableRule = TableRule.builder(config.getName())
                    .actualTables(tableNames)
                    .dataSourceRule(dataSourceRule)
                    .databaseShardingStrategy(new DatabaseShardingStrategy(config.getShardingColumn(), simpleShardingAlgorithm.getDataBaseShardingAlgorithm()))
                    .tableShardingStrategy(new TableShardingStrategy(config.getShardingColumn(),simpleShardingAlgorithm.getTableShardingAlgorithm()))
                    .build();
            tableRuleList.add(tableRule);
            log.info("Build ShardingDataSource: {}", config.getName());
        }
        ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule).tableRules(tableRuleList).build();
        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }

    /**
     * <p>使用hikari连接池</p>
     */
    private DataSource createDataSource(final MyBatisProperties config) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getJdbcUrl());
        hikariConfig.setUsername(config.getUsername());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setPoolName(config.getName());
        hikariConfig.setReadOnly(config.getReadOnly());
        hikariConfig.setMaximumPoolSize(config.getMaximumPoolSize());
        hikariConfig.setDataSourceClassName(config.getDataSourceClassName());
        hikariConfig.setDriverClassName(config.getDriverClassName());
        hikariConfig.setConnectionTimeout(config.getConnectionTimeout());
        hikariConfig.setIdleTimeout(config.getIdleTimeout());
        hikariConfig.setConnectionTestQuery(config.getValidationQuery());
        hikariConfig.setMaxLifetime(config.getMaxLifetime());
        hikariConfig.setMinimumIdle(config.getMinimumIdle());
        return new HikariDataSource(hikariConfig);
    }

    /**
     * <p>初始化配置</p>
     */
    private void initConfig() {
        String defaultDSIndex = EnvironmentManager.getProperty(EnvironmentManager.SHARDING_DEFATULT_DS_INDEX);
        if (defaultDSIndex != null) {
            this.defaultDataSource = ShardingJbdcUtil.generationCurrentDataBaseName(Long.parseLong(defaultDSIndex));
        } else {
            this.defaultDataSource = ShardingJbdcUtil.generationCurrentDataBaseName(0L);
        }
        int index = 0;
        List<MyBatisProperties> configList = new ArrayList<>();
        while (true) {
            if (EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_JDBCURL, index)) != null) {
                configList.add(index, new MyBatisProperties());
                MyBatisProperties config = configList.get(index);
                config.setName(ShardingJbdcUtil.generationCurrentDataBaseName(Long.valueOf(index)));
                config.setDriverClassName(EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_DRIVERCLASSNAME, index)));
                config.setJdbcUrl(EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_JDBCURL, index)));
                config.setUsername(EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_USERNAME, index)));
                config.setPassword(EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_PASSWORD, index)));
                String readOnlyStr = EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_READONLY, index));
                config.setReadOnly(Boolean.parseBoolean(readOnlyStr.trim()));
                String connectionTimeoutStr = EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_CONNECTIONTIMEOUT, index));
                config.setConnectionTimeout(Long.parseLong(connectionTimeoutStr));
                String idleTimeoutStr = EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_IDLETIMEOUT, index));
                config.setIdleTimeout(Long.parseLong(idleTimeoutStr));
                String maxLifetimeStr = EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_MAXLIFETIME, index));
                config.setMaxLifetime(Long.parseLong(maxLifetimeStr));
                String maxImumpoolSizeStr = EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_MAXIMUMPOOSIZE, index));
                config.setMaximumPoolSize(Integer.parseInt(maxImumpoolSizeStr));
                String minimumIdleStr = EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_MINIMUMIDLE, index));
                config.setMinimumIdle(Integer.parseInt(minimumIdleStr));
                String dataSourceClassName = EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_DATASOURCECLASSNAME, index));
                config.setDataSourceClassName(dataSourceClassName);
                String validationQuery = EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_DATASOURCES_VALIDATIONQUERY, index));
                config.setValidationQuery(validationQuery);
            } else {
                break;
            }
            index++;
        }
        this.mapperLocations=EnvironmentManager.getProperty(EnvironmentManager.SHARDING_MAPPERLOCATIONS);
        this.typeAliasesPackage=EnvironmentManager.getProperty(EnvironmentManager.SHARDING_TYPEALIASESPACKAGE);
        index = 0;
        List<TableConfig> tableConfigList = new ArrayList<>();
        while (true) {
            String tableName = EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_TABLECONFIGS_NAME, index));
            if (tableName != null) {
                tableConfigList.add(index, new TableConfig());
                TableConfig config = tableConfigList.get(index);
                config.setName(tableName);
                config.setShardingColumn(EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_TABLECONFIGS_CLOUMN, index)));
                String tableSizeStr = EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_TABLECONFIGS_SIZE, index));
                config.setSize(Long.parseLong(tableSizeStr));
                config.setFormat(EnvironmentManager.getProperty(String.format(EnvironmentManager.SHARDING_TABLECONFIGS_FORMAT, index)));
            } else {
                break;
            }
            index++;
        }
        if (!CollectionUtils.isEmpty(tableConfigList)) {
            this.tableConfigs = tableConfigList;
        }
        if(!CollectionUtils.isEmpty(configList)){
            dataSourceMap = new HashMap<>(configList.size());
            for(MyBatisProperties config: configList){
                dataSourceMap.put(config.getName(),createDataSource(config));
            }
        }
    }
}
