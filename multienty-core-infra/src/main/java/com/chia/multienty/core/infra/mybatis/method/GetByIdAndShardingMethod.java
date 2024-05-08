package com.chia.multienty.core.infra.mybatis.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.chia.multienty.core.infra.sharding.tools.ShardingAlgorithmTool;
import com.chia.multienty.core.mybatis.KutaSqlMethod;
import com.chia.multienty.core.mybatis.generator.ShardingInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class GetByIdAndShardingMethod extends AbstractMethod {
    private static final String SQL_FLAG = " AND `%s`=#{et.%s}";
    private static final String SQL_FLAG_DATE_TIME = " AND (`%s` BETWEEN DATE_FORMAT(#{et.%s}, '%%Y-%%m-%%d 00:00:00') AND DATE_FORMAT(#{et.%s}, '%%Y-%%m-%%d 23:59:59.999'))";

    /**
     * @since 3.5.0
     */
    public GetByIdAndShardingMethod() {
        super(KutaSqlMethod.GET_BY_ID_AND_SHARDING.getName());
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KutaSqlMethod sqlMethod = KutaSqlMethod.GET_BY_ID_AND_SHARDING;
        String tableName = tableInfo.getTableName();

        ShardingInfo shardingInfo = ShardingAlgorithmTool.getShardingInfo(tableName);
        StringBuilder sb = new StringBuilder();
        if(shardingInfo.getShardingDatabase()) {
            sb.append(String.format(SQL_FLAG, shardingInfo.getDatabaseShardingColumnName(), shardingInfo.getDatabaseShardingProperty()));
        }
        if(shardingInfo.getShardingTable()) {
            if(shardingInfo.getTableShardingColumnName().equals("create_time")) {
                sb.append(
                        String.format(SQL_FLAG_DATE_TIME,
                                shardingInfo.getTableShardingColumnName(),
                                shardingInfo.getTableShardingProperty(),
                                shardingInfo.getTableShardingProperty()
                        )
                );
            } else {
                sb.append(String.format(SQL_FLAG, shardingInfo.getTableShardingColumnName(), shardingInfo.getTableShardingProperty()));
            }
        }
        String shardingSql = sb.toString();
        SqlSource sqlSource = super.createSqlSource(configuration, String.format(sqlMethod.getSql(),
                sqlSelectColumns(tableInfo, false),
                tableInfo.getTableName(),
                tableInfo.getKeyColumn(),
                tableInfo.getKeyProperty(),
                shardingSql,
                tableInfo.getLogicDeleteSql(true, true)),
                Object.class);
        return this.addSelectMappedStatementForTable(mapperClass, methodName, sqlSource, tableInfo);
    }
}
