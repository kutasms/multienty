package com.chia.multienty.core.infra.mybatis.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.chia.multienty.core.infra.sharding.tools.ShardingAlgorithmTool;
import com.chia.multienty.core.mybatis.KutaSqlMethod;
import com.chia.multienty.core.mybatis.generator.ShardingInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class UpdateByIdAndShardingMethod extends AbstractMethod {
    /**
     * @since 3.5.0
     */
    public UpdateByIdAndShardingMethod() {
        super(KutaSqlMethod.UPDATE_BY_ID_AND_SHARDING.getName());
    }

    private static final String SQL_FLAG = " AND `%s`=#{et.%s}";
    private static final String SQL_FLAG_DATE_TIME = " AND (`%s` BETWEEN DATE_FORMAT(#{et.%s}, '%%Y-%%m-%%d 00:00:00') AND DATE_FORMAT(#{et.%s}, '%%Y-%%m-%%d 23:59:59.999'))";
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KutaSqlMethod sqlMethod = KutaSqlMethod.UPDATE_BY_ID_AND_SHARDING;
        final String additional = optlockVersion(tableInfo) + tableInfo.getLogicDeleteSql(true, true);
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
        String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(),
                sqlSet(tableInfo.isWithLogicDelete(), false, tableInfo, false, ENTITY, ENTITY_DOT),
                tableInfo.getKeyColumn(), ENTITY_DOT + tableInfo.getKeyProperty(), shardingSql, additional);
        SqlSource sqlSource = super.createSqlSource(configuration, sql, modelClass);
        return addUpdateMappedStatement(mapperClass, modelClass, methodName, sqlSource);
    }
}
