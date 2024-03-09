package com.chia.multienty.core.fusion.sharding.tools;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.client.NacosPropertySource;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.domain.enums.SymbolEnum;
import com.chia.multienty.core.fusion.flyway.util.FlywayUtil;
import com.chia.multienty.core.fusion.sharding.registry.MTShardingAlgorithmProvidedBeanRegistry;
import com.chia.multienty.core.mapper.ShardingTableMapper;
import com.chia.multienty.core.tools.TableCopier;
import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.core.util.TimeUtil;
import com.google.common.base.Strings;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.infra.datasource.props.DataSourceProperties;
import org.apache.shardingsphere.infra.util.expr.InlineExpressionParser;
import org.apache.shardingsphere.infra.util.yaml.YamlEngine;
import org.apache.shardingsphere.infra.yaml.config.swapper.resource.YamlDataSourceConfigurationSwapper;
import org.apache.shardingsphere.infra.yaml.config.swapper.rule.YamlRuleConfigurationSwapperEngine;
import org.apache.shardingsphere.mode.manager.ContextManager;
import org.apache.shardingsphere.sharding.algorithm.config.AlgorithmProvidedShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.spi.KeyGenerateAlgorithm;
import org.apache.shardingsphere.sharding.spi.ShardingAlgorithm;
import org.apache.shardingsphere.sharding.spring.boot.rule.YamlShardingRuleSpringBootConfiguration;
import org.apache.shardingsphere.sharding.yaml.swapper.YamlShardingRuleAlgorithmProviderConfigurationSwapper;
import org.apache.shardingsphere.spring.boot.datasource.AopProxyUtils;
import org.apache.shardingsphere.spring.boot.util.PropertyUtil;
import org.codehaus.groovy.reflection.ReflectionUtils;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.cloud.bootstrap.config.BootstrapPropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.yaml.snakeyaml.Yaml;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.join;

@Slf4j
public class ShardingAlgorithmTool {
    private static final Set<String> tableNameCache = new HashSet<>();

    private static final String FIELD_CONTEXT_MANAGER = "contextManager";
    private static final String FIELD_DATABASE_NAME = "databaseName";

    private static final String PREFIX = "spring";

    private static final YamlDataSourceConfigurationSwapper DATA_SOURCE_SWAPPER = new YamlDataSourceConfigurationSwapper();

    private static final YamlRuleConfigurationSwapperEngine RULE_SWAPPER_ENGINE = new YamlRuleConfigurationSwapperEngine();

    private static final YamlShardingRuleAlgorithmProviderConfigurationSwapper SHARDING_RULE_ALGORITHM_SWAPPER = new YamlShardingRuleAlgorithmProviderConfigurationSwapper();



    @SneakyThrows
    public static List<String> getAllTableNames() {
        ShardingTableMapper mapper = SpringUtil.getBean(ShardingTableMapper.class);
        return mapper.getTableNames();
    }
    @SneakyThrows
    public static String checkExists(String logicTableName, String actualTableName) {
        synchronized (logicTableName.intern()) {
            if(tableNameCache.contains(actualTableName)) {
                return actualTableName;
            }
            tableNameCache.add(actualTableName);
            copyTableForAllDS(logicTableName, actualTableName);
        }
        return actualTableName;
    }

    public static void copyTableForAllDS(String logicTableName, String actualTableName) {
        Map<String, DataSource> dataSourceMap = getDataSourceMap();
        TableCopier tableCopier = SpringUtil.getBean(TableCopier.class);
        dataSourceMap.forEach((k,v) -> {
            try {
                tableCopier.copyTableConstruct(logicTableName, actualTableName, v.getConnection().createStatement());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @SneakyThrows
    public static ContextManager getContextManager(ShardingSphereDataSource dataSource) {
        Field contextManagerField = dataSource.getClass().getDeclaredField(FIELD_CONTEXT_MANAGER);
        contextManagerField.setAccessible(true);
        return (ContextManager) contextManagerField.get(dataSource);
    }

    private void setContextManager(ShardingSphereDataSource dataSource, ContextManager manager) {
        try {
            Field contextManagerField = dataSource.getClass().getDeclaredField(FIELD_CONTEXT_MANAGER);
            contextManagerField.setAccessible(true);
            contextManagerField.set(dataSource, manager);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @SneakyThrows
    public static String getDbName(ShardingSphereDataSource dataSource) {
        Field dbNameField = dataSource.getClass().getDeclaredField(FIELD_DATABASE_NAME);
        ReflectionUtils.makeAccessible(dbNameField);
        Object result = dbNameField.get(dataSource);
        if(result == null) {
            return null;
        }
        return result.toString();
    }

    @SneakyThrows
    public static Map<String, DataSource> getDataSourceMap() {
        DataSource dataSource = SpringUtil.getBean(DataSource.class);
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        if(ds.getDataSources().containsKey(MultientyConstants.DS_SHARDING)) {
            DataSource shardingDS = ds.getDataSources().get(MultientyConstants.DS_SHARDING);
            ShardingSphereDataSource shardingSphereDataSource = (ShardingSphereDataSource) AopProxyUtils.getTarget(shardingDS);
            ContextManager contextManager = getContextManager(shardingSphereDataSource);
            String dbName = getDbName(shardingSphereDataSource);
            return contextManager.getDataSourceMap(dbName);
        }
        return null;
    }



    @SneakyThrows
    public static void refreshShardingConfig(ShardingSphereDataSource dataSource, String configContext, String dataId, String groupId) {
        ContextManager contextManager = getContextManager(dataSource);
        String dbName = getDbName(dataSource);
        Yaml yaml = new Yaml();
        Map<String, Object> rootNode = yaml.loadAs(configContext, Map.class);

        Map<String, Object> springNode = (Map<String, Object>)rootNode.get("spring");
        Map<String, Object> shardingNode = (Map<String, Object>)springNode.get("shardingsphere");
        Map<String, Object> rulesNode = (Map<String, Object>)shardingNode.get("rules");
        YamlShardingRuleSpringBootConfiguration ruleConfig = YamlEngine.unmarshal(yaml.dump(rulesNode), YamlShardingRuleSpringBootConfiguration.class);
        AlgorithmProvidedShardingRuleConfiguration algorithmProvidedShardingRuleConfiguration =
                SHARDING_RULE_ALGORITHM_SWAPPER.swapToObject(ruleConfig.getSharding());


        Map<String, Object> extendedMap = new LinkedHashMap<>();
        expandMap(rootNode, null, extendedMap);
        String psName = String.join(NacosConfigProperties.COMMAS, dataId, groupId);
        MapPropertySource mapPropertySource = new MapPropertySource(psName, extendedMap);
        NacosPropertySource nacosPropertySource = new NacosPropertySource(Arrays.asList(mapPropertySource), groupId, dataId, new Date(), true);
        StandardEnvironment environment = (StandardEnvironment) SpringUtil.getApplicationContext().getEnvironment();

        BootstrapPropertySource<Map<String, Object>> shardingsphereProperties = new BootstrapPropertySource<>(nacosPropertySource);
        if(environment.getPropertySources().contains(psName)) {
            environment.getPropertySources().replace(psName, shardingsphereProperties);
        }
        else {
            environment.getPropertySources().addLast(shardingsphereProperties);
        }
        MTShardingAlgorithmProvidedBeanRegistry algorithmRegistry = new MTShardingAlgorithmProvidedBeanRegistry(environment);
        AnnotationConfigServletWebServerApplicationContext appContext = (AnnotationConfigServletWebServerApplicationContext)
                SpringUtil.getApplicationContext();
        algorithmRegistry.alterAlgorithm(appContext.getDefaultListableBeanFactory());
        Map<String, ShardingAlgorithm> shardingAlgorithmMap = SpringUtil.getApplicationContext().getBeansOfType(ShardingAlgorithm.class);
        Map<String, KeyGenerateAlgorithm> keyGenerateAlgorithmMap =
                SpringUtil.getApplicationContext().getBeansOfType(KeyGenerateAlgorithm.class);
        algorithmProvidedShardingRuleConfiguration.setShardingAlgorithms(shardingAlgorithmMap);
        algorithmProvidedShardingRuleConfiguration.setKeyGenerators(keyGenerateAlgorithmMap);
        Map<String, DataSourceProperties> dataSourceProperties = getDataSourceProperties(environment);
        contextManager.alterDataSourceAndRuleConfiguration(dbName, dataSourceProperties, Arrays.asList(algorithmProvidedShardingRuleConfiguration));
        // 创建DataSource之后执行migrate
        migrate(dbName, contextManager);
        contextManager.reloadDatabase(dbName);
        log.info("[Multi Tenant Starter] shardingsphere config update completed.");
    }

    private static void migrate(String databaseName, ContextManager contextManager) {
        DynamicRoutingDataSource dynamicRoutingDataSource = (DynamicRoutingDataSource) SpringUtil.getBean(DataSource.class);
        Map<String, DataSource> dataSourceMap = contextManager.getDataSourceMap(databaseName);
        dataSourceMap.forEach((k,v) -> {
            FlywayUtil.migrateShardingDataSource(dynamicRoutingDataSource, v);
        });
    }

    private static void expandMap(Map<String, Object> map, String prefix,Map<String, Object> fullNameMap) {

        for(Map.Entry<String, Object> entry : map.entrySet()) {
            String currentPrefix = (prefix == null ? "" : prefix + SymbolEnum.DOT.getCode()) + entry.getKey();
            if(entry.getValue() instanceof Map) {
                expandMap(
                        (Map<String, Object>)entry.getValue(),
                        currentPrefix,
                        fullNameMap);
            } else {
                if(entry.getValue() instanceof ArrayList) {
                    ArrayList<?> list = (ArrayList<?>) entry.getValue();
                    for(int i=0; i< list.size();i++) {
                        String listPrefix = String.format("%s[%s]", currentPrefix, i);
                        fullNameMap.put(listPrefix, list.get(i));
                    }
                } else {
                    fullNameMap.put(currentPrefix, entry.getValue());
                }
            }
        }
    }

    public static Map<String, DataSourceProperties> getDataSourceProperties(StandardEnvironment environment) {
        Map<String, DataSourceProperties> map = new HashMap<>();
        List<String> dataSourceNames = getDataSourceNames(environment);
        for (String dataSourceName : dataSourceNames) {
            Map<String, Object> dataSourceProps = PropertyUtil.handle(environment, join("", DATASOURCE_PREFIX, dataSourceName), Map.class);
            DataSourceProperties properties = new DataSourceProperties(dataSourceProps.get(DATA_SOURCE_TYPE).toString(), PropertyUtil.getCamelCaseKeys(dataSourceProps));
            map.put(dataSourceName, properties);
        }
        return map;
    }


    private static final String DATASOURCE_PREFIX = "spring.shardingsphere.dataSource.";

    private static final String DATA_SOURCE_NAME = "name";

    private static final String DATA_SOURCE_NAMES = "names";

    private static final String DATA_SOURCE_TYPE = "type";

    private static final String JNDI_NAME = "jndi-name";

    private static List<String> getDataSourceNames(final StandardEnvironment standardEnv) {
        standardEnv.setIgnoreUnresolvableNestedPlaceholders(true);
        String dataSourceNames = standardEnv.getProperty(DATASOURCE_PREFIX + DATA_SOURCE_NAME);
        if (Strings.isNullOrEmpty(dataSourceNames)) {
            dataSourceNames = standardEnv.getProperty(DATASOURCE_PREFIX + DATA_SOURCE_NAMES);
        }
        return new InlineExpressionParser(dataSourceNames).splitAndEvaluate();
    }

    private static DataSource getJNDIDataSource(final String jndiName) throws NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setResourceRef(true);
        bean.setJndiName(jndiName);
        bean.setProxyInterface(DataSource.class);
        bean.afterPropertiesSet();
        return (DataSource) AopProxyUtils.getTarget(bean.getObject());
    }



    public static void reload() {
        List<String> list = getAllTableNames();
        tableNameCache.clear();
        tableNameCache.addAll(list);
    }

    private static Map<String, String> waitCopyTables = new HashMap<>();

    public static void copyTablesAfterStarted() {
        if(waitCopyTables.size() > 0) {
            waitCopyTables.forEach((k,v) -> {
                copyTableForAllDS(k, v);
            });
        }
    }

    public static List<String> tableNames(String logicTableName) {
        List<String> names = new ArrayList<>();
        String prefix = logicTableName + "_";
        String currentMonthSuffix = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
//        if(tableNameCache.size() == 0) {
//            reload();
//        }
//        for (String tableName : tableNameCache) {
//            if(tableName.startsWith(prefix)) {
//                String[] split = tableName.split("_");
//                names.add(split[split.length-1]);
//            }
//        }
        if(!names.contains(currentMonthSuffix)) {
            waitCopyTables.put(logicTableName, prefix + currentMonthSuffix);
            names.add(currentMonthSuffix);
        }
        return names;
    }

    /**
     * 从行表达式中解析表名称列表
     * */
    public static List<String> resolveTableNames(String expression) {
        InlineExpressionParser parser = new InlineExpressionParser(expression);
        List<String> names = parser.splitAndEvaluate();
        return names;
    }


    public static List<String> twoYearsCalendar() {
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusYears(2);
        List<String> result = new ArrayList<>();
        List<LocalDate> localDates = TimeUtil.everyDayBetween(start, end);
        return localDates.stream().map(m-> TimeUtil.to6(m)).collect(Collectors.toList());
    }

}
