package com.wz.config;

/**
 * <p>数据表属性信息</p>
 * Created by wangzi on 2017-08-22.
 */
public class TableConfig {
    private String name;
    private String shardingColumn;
    private Long size;
    private String format;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShardingColumn() {
        return shardingColumn;
    }

    public void setShardingColumn(String shardingColumn) {
        this.shardingColumn = shardingColumn;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
