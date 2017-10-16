package com.wz.config;

import lombok.Data;

/**
 * <p>数据表属性信息</p>
 *
 * @author wangzi
 */
@Data
public class TableConfig {
    private String name;
    private String shardingColumn;
    private Long size;
    private String format;
}
