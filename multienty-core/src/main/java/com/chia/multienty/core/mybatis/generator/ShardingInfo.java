package com.chia.multienty.core.mybatis.generator;

import lombok.Data;

@Data
public class ShardingInfo {
    private Boolean shardingTable = false;
    private Boolean shardingDatabase = false;
    private String databaseShardingColumnName;
    private String databaseShardingProperty;
    private String tableShardingColumnName;
    private String tableShardingProperty;
}
