package com.chia.multienty.core.infra.sharding.algorithm;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.Collection;
import java.util.Properties;

public class MTDatabaseShardingAlgorithm implements StandardShardingAlgorithm<Long> {

    private Properties properties;

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        long suffix = shardingValue.getValue() % 2 + 1;
        String actualDatabaseName = shardingValue.getLogicTableName() + "_" + suffix;
        if(!availableTargetNames.contains(actualDatabaseName)) {
            availableTargetNames.add(actualDatabaseName);
        }

        return actualDatabaseName;
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        return null;
    }

    @Override
    public Properties getProps() {
        return this.properties;
    }

    @Override
    public void init(Properties props) {
        this.properties = props;
    }
}
