package com.chia.multienty.core.infra.mybatis.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.chia.multienty.core.infra.sharding.tools.ShardingAlgorithmTool;
import com.chia.multienty.core.mybatis.KutaSqlMethod;
import com.chia.multienty.core.mybatis.generator.ShardingInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class GetListBySharding extends AbstractMethod {
    private static final String SQL_FLAG = " AND `%s`=#{ew.entity.%s}";
    private static final String SQL_FLAG_DATE_TIME = " AND (`%s` BETWEEN DATE_FORMAT(#{ew.entity.%s}, '%%Y-%%m-%%d 00:00:00') AND DATE_FORMAT(#{ew.entity.%s}, '%%Y-%%m-%%d 23:59:59.999'))";

    /**
     * @since 3.5.0
     */
    public GetListBySharding() {
        super(KutaSqlMethod.GET_LIST_BY_SHARDING.getName());
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

        KutaSqlMethod sqlMethod = KutaSqlMethod.GET_LIST_BY_SHARDING;
        String sql = String.format(sqlMethod.getSql(), sqlFirst(), sqlSelectColumns(tableInfo, true), tableInfo.getTableName(),
                sqlWhereEntityWrapper(true, tableInfo), sqlOrderBy(tableInfo), sqlComment());
        SqlSource sqlSource = super.createSqlSource(configuration, sql, modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, methodName, sqlSource, tableInfo);
    }

    @Override
    protected String sqlWhereEntityWrapper(boolean newLine, TableInfo table) {
        /*
         * Wrapper SQL
         */
        String _sgEs_ = "<bind name=\"_sgEs_\" value=\"ew.sqlSegment != null and ew.sqlSegment != ''\"/>";
        String andSqlSegment = SqlScriptUtils.convertIf(String.format(" AND ${%s}", WRAPPER_SQLSEGMENT), String.format("_sgEs_ and %s", WRAPPER_NONEMPTYOFNORMAL), true);
        String lastSqlSegment = SqlScriptUtils.convertIf(String.format(" ${%s}", WRAPPER_SQLSEGMENT), String.format("_sgEs_ and %s", WRAPPER_EMPTYOFNORMAL), true);

        /*
         * 存在逻辑删除 SQL 注入
         */
        if (table.isWithLogicDelete()) {
            String sqlScript = table.getAllSqlWhere(true, true, true, WRAPPER_ENTITY_DOT);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER_ENTITY), true);
            sqlScript = SqlScriptUtils.convertIf(_sgEs_ + NEWLINE + sqlScript + NEWLINE + andSqlSegment + NEWLINE + lastSqlSegment,
                    String.format("%s != null", WRAPPER), true);
            sqlScript = SqlScriptUtils.convertWhere(table.getLogicDeleteSql(false, true) + NEWLINE + sqlScript);
            return newLine ? NEWLINE + sqlScript : sqlScript;
        }

        /*
         * 普通 SQL 注入
         */
        String sqlScript = table.getAllSqlWhere(false, false, true, WRAPPER_ENTITY_DOT);
        sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER_ENTITY), true);
        sqlScript = SqlScriptUtils.convertWhere(sqlScript + NEWLINE + andSqlSegment) + NEWLINE + lastSqlSegment;
        sqlScript = SqlScriptUtils.convertIf(_sgEs_ + NEWLINE + sqlScript, String.format("%s != null", WRAPPER), true);
        return newLine ? NEWLINE + sqlScript : sqlScript;
    }
}
