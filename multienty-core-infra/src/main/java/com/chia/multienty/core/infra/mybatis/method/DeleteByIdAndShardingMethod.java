package com.chia.multienty.core.infra.mybatis.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.chia.multienty.core.infra.sharding.tools.ShardingAlgorithmTool;
import com.chia.multienty.core.mybatis.KutaSqlMethod;
import com.chia.multienty.core.mybatis.generator.ShardingInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class DeleteByIdAndShardingMethod extends AbstractMethod {
    private static final String SQL_FLAG = " AND `%s`=#{et.%s}";
    private static final String SQL_FLAG_DATE_TIME = " AND (`%s` BETWEEN DATE_FORMAT(#{et.%s}, '%%Y-%%m-%%d 00:00:00') AND DATE_FORMAT(#{et.%s}, '%%Y-%%m-%%d 23:59:59.999'))";

    /**
     * @since 3.5.0
     */
    public DeleteByIdAndShardingMethod() {
        super(KutaSqlMethod.DELETE_BY_ID_AND_SHARDING.getName());
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
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
        String sql;
        KutaSqlMethod sqlMethod = KutaSqlMethod.LOGIC_DELETE_BY_ID_AND_SHARDING;
        if(tableInfo.isWithLogicDelete()) {
            sql = logicDeleteScript(tableInfo, sqlMethod, shardingSql);
            SqlSource sqlSource = super.createSqlSource(configuration, sql, Object.class);
            return addUpdateMappedStatement(mapperClass, modelClass, methodName, sqlSource);
        } else {
            sqlMethod = KutaSqlMethod.DELETE_BY_ID_AND_SHARDING;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(),
                    tableInfo.getKeyProperty(), shardingSql);
            SqlSource sqlSource = super.createSqlSource(configuration, sql, Object.class);
            return this.addDeleteMappedStatement(mapperClass, methodName, sqlSource);
        }
    }

    public String logicDeleteScript(TableInfo tableInfo, KutaSqlMethod sqlMethod, String shardingSql) {
        return String.format(sqlMethod.getSql(), tableInfo.getTableName(),
                sqlLogicSet(tableInfo), tableInfo.getKeyColumn(), tableInfo.getKeyProperty(),
                shardingSql, tableInfo.getLogicDeleteSql(true, true));
    }
}
