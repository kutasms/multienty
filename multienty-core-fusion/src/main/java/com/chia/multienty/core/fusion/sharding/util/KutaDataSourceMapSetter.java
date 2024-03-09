package com.chia.multienty.core.fusion.sharding.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.infra.datasource.pool.creator.DataSourcePoolCreator;
import org.apache.shardingsphere.infra.datasource.props.DataSourceProperties;
import org.apache.shardingsphere.infra.util.expr.InlineExpressionParser;
import org.apache.shardingsphere.spring.boot.datasource.AopProxyUtils;
import org.apache.shardingsphere.spring.boot.util.PropertyUtil;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KutaDataSourceMapSetter {
    private static final String PREFIX = "spring.shardingsphere.datasource.";

    private static final String DATA_SOURCE_NAME = "name";

    private static final String DATA_SOURCE_NAMES = "names";

    private static final String DATA_SOURCE_TYPE = "type";

    private static final String JNDI_NAME = "jndi-name";

    public static Map<String, DataSource> getDataSourceMap(final Environment environment) throws ReflectiveOperationException, NamingException {
        Map<String, DataSource> result = new LinkedHashMap<>();
        for (String each : getDataSourceNames(environment)) {
            result.put(each, getDataSource(environment, each));
        }
        return result;
    }

    private static List<String> getDataSourceNames(final Environment environment) {
        StandardEnvironment standardEnv = (StandardEnvironment) environment;
        standardEnv.setIgnoreUnresolvableNestedPlaceholders(true);
        String dataSourceNames = standardEnv.getProperty(PREFIX + DATA_SOURCE_NAME);
        if (Strings.isNullOrEmpty(dataSourceNames)) {
            dataSourceNames = standardEnv.getProperty(PREFIX + DATA_SOURCE_NAMES);
        }
        return new InlineExpressionParser(dataSourceNames).splitAndEvaluate();
    }

    @SuppressWarnings("unchecked")
    private static DataSource getDataSource(final Environment environment, final String dataSourceName) throws ReflectiveOperationException, NamingException {
        Map<String, Object> dataSourceProps = PropertyUtil.handle(environment, String.join("", PREFIX, dataSourceName), Map.class);
        Preconditions.checkState(!dataSourceProps.isEmpty(), "Wrong datasource [%s] properties.", dataSourceName);
        if (dataSourceProps.containsKey(JNDI_NAME)) {
            return getJNDIDataSource(dataSourceProps.get(JNDI_NAME).toString());
        }
        return DataSourcePoolCreator.create(new DataSourceProperties(dataSourceProps.get(DATA_SOURCE_TYPE).toString(), PropertyUtil.getCamelCaseKeys(dataSourceProps)));
    }

    private static DataSource getJNDIDataSource(final String jndiName) throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setResourceRef(true);
        bean.setJndiName(jndiName);
        bean.setProxyInterface(DataSource.class);
        bean.afterPropertiesSet();
        return (DataSource) AopProxyUtils.getTarget(bean.getObject());
    }
}
