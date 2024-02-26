package com.chia.multienty.core.sharding.algorithm;

import com.chia.multienty.core.sharding.tools.ShardingAlgorithmTool;
import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CreateTimeShardingAlgorithm implements StandardShardingAlgorithm<LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Properties properties;

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<LocalDateTime> shardingValue) {
        String tableSuffix = shardingValue.getValue().format(DateTimeFormatter.ofPattern("yyyyMM"));
        String logicTableName = shardingValue.getDataNodeInfo().getPrefix();
        String actualTableName = logicTableName.concat(tableSuffix);

        String result = ShardingAlgorithmTool.checkExists(logicTableName, actualTableName);
        if(!availableTargetNames.contains(result)) {
            availableTargetNames.add(result);
        }
        return result;
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<LocalDateTime> shardingValue) {
        Range<LocalDateTime> valueRange = shardingValue.getValueRange();

        LocalDateTime start = valueRange.lowerEndpoint();
        LocalDateTime end = valueRange.upperEndpoint();

        if(end.isAfter(LocalDateTime.now())) {
            end = LocalDateTime.now();
        }

        Set<String> calcNames = calcTableNames(shardingValue.getDataNodeInfo().getPrefix(), start, end);

        ArrayList<String> tables = new ArrayList<>(calcNames);
        for (String table : tables) {
            if(!availableTargetNames.contains(table)) {
                availableTargetNames.add(table);
            }
        }
        return tables;
    }

    public Set<String> calcTableNames(String prefix, LocalDateTime lower, LocalDateTime upper) {
        Set<String> range = new HashSet<>();

        while(lower.isBefore(upper)) {
            String tableName = getTableNameByDate(lower, prefix);
            range.add(tableName);
            lower = lower.plusMonths(1);
        }

        String tableName = getTableNameByDate(upper, prefix);
        range.add(tableName);

        return range;
    }

    private String getTableNameByDate(LocalDateTime datetime, String prefix) {
        String tableSuffix = datetime.format(DateTimeFormatter.ofPattern("yyyyMM"));
        return prefix.concat(tableSuffix);
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
