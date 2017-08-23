package com.wz.config;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>工具类，用以生成数据源名称和数据表名称</p>
 * Created by wangzi on 2017-08-22.
 */
public class ShardingJbdcUtil {
    public static final String TABLE_FORMAT = "_%04d";
    public static final String DATA_SOURCE_NAME_FORMAT = "shardingds%04d";

    /**
     * <p>根据format生成当前所需的数据源名称</p>
     *
     * @param index
     * @return
     */
    public static String generationCurrentDataBaseName(Long index) {
        return String.format(DATA_SOURCE_NAME_FORMAT, index);
    }

    /**
     * <p>根据format生成表名称</p>
     *
     * @param tableName
     * @param tableSize
     * @param format
     * @return
     */
    public static List<String> generationTableNames(String tableName, Long tableSize, String format) {
        if (format == null) {
            format = TABLE_FORMAT;
        }
        List<String> tableNames = new ArrayList<>();
        for (int i = 0; i < tableSize; i++) {
            tableNames.add(tableName + String.format(format, i));
        }
        return tableNames;
    }

    /**
     * <p>根据format生成当前所需的表名</p>
     *
     * @param tableName
     * @param tableNumber
     * @param format
     * @return
     */
    public static String generationCurrentTableName(String tableName, Long tableNumber, String format) {
        if (format == null) {
            format = TABLE_FORMAT;
        }
        return tableName + String.format(format, tableNumber);
    }
}
