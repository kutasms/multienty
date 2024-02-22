package com.chia.multienty.core.flyway.util;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.chia.multienty.core.flyway.proerties.FlywayExtensionProperties;
import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.core.domain.constants.MultiTenantConstants;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.sharding.domain.ShardingSphereConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.mode.manager.ContextManager;
import org.apache.shardingsphere.spring.boot.datasource.AopProxyUtils;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class FlywayUtil {
    public static void migrateDynamicDataSource(DynamicRoutingDataSource ds) {
        Map<String, DataSource> dataSources = ds.getDataSources();

        YamlMultiTenantProperties properties = SpringUtil.getBean(YamlMultiTenantProperties.class);
        FlywayExtensionProperties flywayExtProperties = SpringUtil.getBean(FlywayExtensionProperties.class);
        dataSources.forEach((k,v) -> {
            if(!ShardingSphereConstants.DATASOURCE_NAME.equals(k)) {
                migrateNonShardingDataSource(k, v, ds, properties, flywayExtProperties);
            } else {
                migrateShardingDataSource(v, ds, properties,flywayExtProperties);
            }
        });
    }

    private static void migrateNonShardingDataSource(String dataSourceName, DataSource ds,
                                                    DynamicRoutingDataSource dynamicRoutingDataSource,
                                                    YamlMultiTenantProperties properties,
                                                    FlywayExtensionProperties flywayExtProperties) {
        if(dataSourceName.startsWith(properties.getStandaloneTenantDbNamePrefix())) {
            // 独立租户数据库
        }
        try {
            if(ds.getConnection().getMetaData().getUserName().startsWith("root@")) {
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(dataSourceName.contains("_")) {
            String[] dbSplit = dataSourceName.split("_");
            String location = flywayExtProperties.getLocationPrefix().concat(dbSplit[dbSplit.length-1]);
            Flyway flyway = Flyway.configure()
                    .dataSource(ds)
                    .baselineOnMigrate(true)
                    .outOfOrder(true)
                    .locations(location)
                    .load();
            ensureMigrateSuccess(flyway, dynamicRoutingDataSource, ds);
            log.info("Datasource {} migrate operation executed successful.", dataSourceName);
        } else {
            log.info("Data source {} does not require migration operation", dataSourceName);
        }
    }

    public static void migrateShardingDataSource(DynamicRoutingDataSource dynamicRoutingDataSource) {
        YamlMultiTenantProperties properties = SpringUtil.getBean(YamlMultiTenantProperties.class);
        FlywayExtensionProperties flywayExtProperties = SpringUtil.getBean(FlywayExtensionProperties.class);
        migrateShardingDataSource(dynamicRoutingDataSource.getDataSource(MultiTenantConstants.DS_SHARDING),
                dynamicRoutingDataSource,
                properties, flywayExtProperties);
    }

    public static void migrateShardingDataSource(DynamicRoutingDataSource dynamicRoutingDataSource,DataSource shardingInnerDataSource) {
        YamlMultiTenantProperties properties = SpringUtil.getBean(YamlMultiTenantProperties.class);
        FlywayExtensionProperties flywayExtProperties = SpringUtil.getBean(FlywayExtensionProperties.class);
        String location = null;
        try {
            String pattern = "jdbc\\:mysql\\:\\/\\/[\\w\\.]+\\:\\d+/(\\w+?)\\?";
            String url = shardingInnerDataSource.getConnection().getMetaData().getURL();
            Pattern compile = Pattern.compile(pattern);
            Matcher m = compile.matcher(url);
            if(m.find()) {
                String shardingDBName = m.group(1);
                if(shardingDBName.startsWith(properties.getStandaloneTenantDbNamePrefix())) {
                    // 独立站
                    location = flywayExtProperties.getLocationPrefix() + MultiTenantConstants.SHARDING_FLYWAY_SQL_DIR +
                            shardingDBName.split("_")[2]; // ks_da_[order]_1
                } else {
                    location = flywayExtProperties.getLocationPrefix() + MultiTenantConstants.SHARDING_FLYWAY_SQL_DIR +
                            shardingDBName.split("_")[1]; // ks_[order]_1
                }
                Flyway flyway = Flyway.configure()
                        .dataSource(shardingInnerDataSource)
                        .baselineOnMigrate(true)
                        .outOfOrder(true)
                        .locations(location)
                        .load();
                ensureMigrateSuccess(flyway, dynamicRoutingDataSource, shardingInnerDataSource);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void migrateShardingDataSource(DataSource ds,
                                                    DynamicRoutingDataSource dynamicRoutingDataSource,
                                                    YamlMultiTenantProperties properties,
                                                    FlywayExtensionProperties flywayExtProperties) {
        ShardingSphereDataSource shardingSphereDataSource = (ShardingSphereDataSource) AopProxyUtils.getTarget(ds);
        Field[] fields = shardingSphereDataSource.getClass().getDeclaredFields();
        Field contextManagerField = Arrays.stream(fields).filter(p->p.getName().equals("contextManager")).findFirst().get();
        Field dbNameField = Arrays.stream(fields).filter(p->p.getName().equals("databaseName")).findFirst().get();
        ReflectionUtils.makeAccessible(contextManagerField);
        ReflectionUtils.makeAccessible(dbNameField);
        try {
            ContextManager contextManager = (ContextManager)contextManagerField.get(shardingSphereDataSource);
            String dbName = dbNameField.get(shardingSphereDataSource).toString();
            Map<String, DataSource> dataSourceMap = contextManager.getDataSourceMap(dbName);
            if(null != dataSourceMap && !dataSourceMap.isEmpty()) {
                dataSourceMap.forEach((name, shardingDs) -> {
                    String location = null;
                    try {
                        String pattern = "jdbc\\:mysql\\:\\/\\/[\\w\\.]+\\:\\d+/(\\w+?)\\?";
                        String url = shardingDs.getConnection().getMetaData().getURL();
                        Pattern compile = Pattern.compile(pattern);
                        Matcher m = compile.matcher(url);
                        if(m.find()) {
                            String shardingDBName = m.group(1);
                            if(shardingDBName.startsWith(properties.getStandaloneTenantDbNamePrefix())) {
                                // 独立站
                                location = flywayExtProperties.getLocationPrefix() + MultiTenantConstants.SHARDING_FLYWAY_SQL_DIR +
                                        shardingDBName.split("_")[2]; // ks_da_[order]_1
                            } else {
                                location = flywayExtProperties.getLocationPrefix() + MultiTenantConstants.SHARDING_FLYWAY_SQL_DIR +
                                        shardingDBName.split("_")[1]; // ks_[order]_1
                            }
                            Flyway flyway = Flyway.configure()
                                    .dataSource(shardingDs)
                                    .baselineOnMigrate(true)
                                    .outOfOrder(true)
                                    .locations(location)
                                    .load();
                            ensureMigrateSuccess(flyway, dynamicRoutingDataSource, shardingDs);
                        }

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    private static void ensureMigrateSuccess(Flyway flyway, DynamicRoutingDataSource dynamicDS, DataSource currentDS) {
        try{
            flyway.migrate();
        }
        catch (FlywayException ex) {
            log.error("Flyway.migrate发生异常",ex);
            Throwable throwable = ex.getCause();
            while (throwable != null && throwable.getCause() != null) {
                throwable = throwable.getCause();
            }

            if(throwable.getMessage().startsWith("SELECT command denied to user")) {
                // TODO: GRANT SELECT ON `performance_schema`.user_variables_by_thread TO '帐号'@'%';
//                            log.error(ex.getMessage(), ex);
                DataSource dsRoot = dynamicDS.getDataSource("ds_root");
                try {

                    Connection conn = dsRoot.getConnection();
                    Statement statement = conn.createStatement();
                    String sql = "GRANT SELECT ON `performance_schema`.user_variables_by_thread TO '"
                            + currentDS.getConnection().getMetaData().getUserName().split("@")[0] + "'@'%';flush privileges;";
                    statement.execute(sql);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                ensureMigrateSuccess(flyway, dynamicDS, currentDS);
                return;
            }
        }
    }
}
