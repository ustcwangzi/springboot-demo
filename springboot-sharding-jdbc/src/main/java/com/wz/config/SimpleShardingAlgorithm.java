package com.wz.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>分库分表规则</p>
 * Created by wangzi on 2017-08-22.
 */
@Slf4j
public class SimpleShardingAlgorithm {
    private TableConfig tableConfig;
    private DatabaseShardingAlgorithm dataBaseShardingAlgorithm = new DatabaseShardingAlgorithm();
    private TableShardingAlgorithm tableShardingAlgorithm = new TableShardingAlgorithm();

    public SimpleShardingAlgorithm(TableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }

    public DatabaseShardingAlgorithm getDataBaseShardingAlgorithm() {
        return dataBaseShardingAlgorithm;
    }

    public TableShardingAlgorithm getTableShardingAlgorithm() {
        return tableShardingAlgorithm;
    }

    /**
     * <p>分库规则</p>
     * <p>分库分表键对dbSize取余得到库下标</p>
     */
    class DatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {
        /**
         * SQL中 = 操作触发
         *
         * @param collection    所有数据源名称集合
         * @param shardingValue 包含逻辑表名、列名、分库分表键等
         * @return 实际数据源名称
         */
        @Override
        public String doEqualSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
            Long dbSize = (long) collection.size(); //库数量
            Long value = Long.parseLong(shardingValue.getValue().toString()); //分库分表键
            Long ds = value % dbSize;
            log.debug("key:{}, ds:{}", value, ds);
            return ShardingJbdcUtil.generationCurrentDataBaseName(ds);
        }

        @Override
        public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
            Set<String> dbNameSet = new HashSet<>();
            for (Object e : shardingValue.getValues()) {
                Long value = Long.parseLong(e.toString());
                String dbName = doEqualSharding(collection, new ShardingValue<Long>(shardingValue.getLogicTableName(), shardingValue.getColumnName(), value));
                dbNameSet.add(dbName);
            }
            return dbNameSet;
        }

        @Override
        public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
            Set<String> dbNameSet = new HashSet<>();
            Range<Long> range = shardingValue.getValueRange();
            Object lowerEndpointNumber = range.lowerEndpoint();
            Object upperEndpointNumber = range.upperEndpoint();
            for (Long i = Long.parseLong(lowerEndpointNumber.toString()); i <= Long.parseLong(upperEndpointNumber.toString()); i++) {
                String dbName = doEqualSharding(collection, new ShardingValue<Long>(shardingValue.getLogicTableName(), shardingValue.getColumnName(), i));
                dbNameSet.add(dbName);
            }
            return dbNameSet;
        }
    }

    /**
     * <p>分表规则</p>
     * <p>分库分表键对tableSize取余得到表下标</p>
     */
    class TableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {
        @Override
        public String doEqualSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
            Long tableSize = tableConfig.getSize();
            Long value = Long.parseLong(shardingValue.getValue().toString());
            Long moduloValue = value % tableSize;
            log.debug("key:{}, tb:{}", value, moduloValue);
            return ShardingJbdcUtil.generationCurrentTableName(shardingValue.getLogicTableName(), moduloValue, tableConfig.getFormat());
        }

        @Override
        public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
            Set<String> tableNameSet = new HashSet<>();
            for (Object e : shardingValue.getValues()) {
                Long value = Long.parseLong(e.toString());
                String tableName = doEqualSharding(collection, new ShardingValue<Long>(shardingValue.getLogicTableName(), shardingValue.getColumnName(), value));
                tableNameSet.add(tableName);
            }
            return tableNameSet;
        }

        @Override
        public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<Long> shardingValue) {
            Set<String> tableNameSet = new HashSet<>();
            Range<Long> range = shardingValue.getValueRange();
            Object lowerEndpointNumber = range.lowerEndpoint();
            Object upperEndpointNumber = range.upperEndpoint();
            for (Long i = Long.parseLong(lowerEndpointNumber.toString()); i <= Long.parseLong(upperEndpointNumber.toString()); i++) {
                String tableName = doEqualSharding(collection, new ShardingValue<Long>(shardingValue.getLogicTableName(), shardingValue.getColumnName(), i));
                tableNameSet.add(tableName);
            }
            return tableNameSet;
        }
    }
}
