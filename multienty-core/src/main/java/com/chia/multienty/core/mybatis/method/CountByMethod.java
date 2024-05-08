package com.chia.multienty.core.mybatis.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.chia.multienty.core.mybatis.KutaSqlMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class CountByMethod extends AbstractMethod {
    public CountByMethod() {
        super("countBy");
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = String.format(KutaSqlMethod.COUNT_BY.getSql(),
                sqlFirst(),
                tableInfo.getTableName(),
                sqlWhereEntityWrapper(true, tableInfo), sqlComment());


        String methodName = KutaSqlMethod.COUNT_BY.getName();
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return this.addSelectMappedStatementForOther(mapperClass, methodName, sqlSource, Long.class);
    }
}
