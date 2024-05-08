package com.chia.multienty.core.mybatis.method;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.mybatis.KutaSqlMethod;
import com.chia.multienty.core.mybatis.SFunctionArrayTypeHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Map;

public class GetByPrimaryKeyMethod extends AbstractMethod {
    public GetByPrimaryKeyMethod() {
        super(KutaSqlMethod.GET_BY_PRIMARY_KEY.getName());
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = String.format(KutaSqlMethod.GET_BY_PRIMARY_KEY.getSql(),
                tableInfo.getTableName(),
                tableInfo.getKeyColumn()
        );
        this.configuration.getTypeHandlerRegistry().register(SFunction[].class, SFunctionArrayTypeHandler.class);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Map.class);
        return this.addSelectMappedStatementForTable(mapperClass, KutaSqlMethod.GET_BY_PRIMARY_KEY.getName(), sqlSource, tableInfo);
    }


}
