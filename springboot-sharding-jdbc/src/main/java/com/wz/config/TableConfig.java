package com.wz.config;

import lombok.Data;

/**
 * <p>数据表属性信息</p>
 * Created by wangzi on 2017-08-22.
 */
@Data
public class TableConfig {
    private String name;
    private String shardingColumn;
    private Long size;
    private String format;
}
