package com.chia.multienty.core.mybatis;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import org.apache.ibatis.reflection.property.PropertyNamer;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.joining;

@MappedTypes(SFunction[].class)
public class SFunctionArrayTypeHandler extends BaseTypeHandler<SFunction[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SFunction[] parameter, JdbcType jdbcType) throws SQLException {
        String fields = columnsToString(parameter);
        ps.setString(i, fields);
    }

    @Override
    public SFunction[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new SFunction[0];
    }

    @Override
    public SFunction[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new SFunction[0];
    }

    @Override
    public SFunction[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new SFunction[0];
    }

    private Map<String, ColumnCache> columnMap = null;
    private boolean initColumnMap = false;

    private Object entity;
    private Class<?> entityClass;

    protected String columnsToString(SFunction<?, ?>... columns) {
        return columnsToString(true, columns);
    }


    protected String columnsToString(boolean onlyColumn, SFunction<?, ?>... columns) {
        return Arrays.stream(columns).map(i -> columnToString(i, onlyColumn)).collect(joining(StringPool.COMMA));
    }



    protected String columnToString(SFunction<?, ?> column, boolean onlyColumn) {
        return getColumn(LambdaUtils.extract(column), onlyColumn);
    }

    public Class<?> getEntityClass() {
        if (entityClass == null && entity != null) {
            entityClass = (Class<?>) entity.getClass();
        }
        return entityClass;
    }
    /**
     * 获取 SerializedLambda 对应的列信息，从 lambda 表达式中推测实体类
     * <p>
     * 如果获取不到列信息，那么本次条件组装将会失败
     *
     * @param lambda     lambda 表达式
     * @param onlyColumn 如果是，结果: "name", 如果否： "name" as "name"
     * @return 列
     * @throws com.baomidou.mybatisplus.core.exceptions.MybatisPlusException 获取不到列信息时抛出异常
     * @see LambdaMeta#getInstantiatedClass()
     * @see LambdaMeta#getImplMethodName()
     */
    private String getColumn(LambdaMeta lambda, boolean onlyColumn) {
        Class<?> aClass = lambda.getInstantiatedClass();
        tryInitCache(aClass);
        String fieldName = PropertyNamer.methodToProperty(lambda.getImplMethodName());
        ColumnCache columnCache = getColumnCache(fieldName, aClass);
        return onlyColumn ? columnCache.getColumn() : columnCache.getColumnSelect();
    }

    private void tryInitCache(Class<?> lambdaClass) {
        columnMap = LambdaUtils.getColumnMap(lambdaClass);
    }

    private ColumnCache getColumnCache(String fieldName, Class<?> lambdaClass) {
        ColumnCache columnCache = columnMap.get(LambdaUtils.formatKey(fieldName));
        Assert.notNull(columnCache, "can not find lambda cache for this property [%s] of entity [%s]",
                fieldName, lambdaClass.getName());
        return columnCache;
    }
}
