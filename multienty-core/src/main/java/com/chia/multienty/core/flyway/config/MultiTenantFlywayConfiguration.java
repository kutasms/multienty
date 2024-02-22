package com.chia.multienty.core.flyway.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.chia.multienty.core.flyway.proerties.FlywayExtensionProperties;
import com.chia.multienty.core.flyway.util.FlywayUtil;
import com.chia.multienty.core.flyway.condition.FlywayExtensionCondition;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@EnableConfigurationProperties(FlywayExtensionProperties.class)
@ConditionalOnClass({
        FlywayExtensionProperties.class,
        DataSource.class
})
@Conditional(FlywayExtensionCondition.class)
@Configuration
@EnableTransactionManagement
public class MultiTenantFlywayConfiguration {
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private FlywayExtensionProperties properties;

    @Autowired
    private YamlMultiTenantProperties yamlMultiTenantProperties;
    @Autowired
    private DataSource dataSource;

    @Bean
    @SneakyThrows
    public void migrate() {
        log.info(">>>>>>>>> Use flyway to migrate each data source in the dynamic data source ");
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        FlywayUtil.migrateDynamicDataSource(ds);
    }
}
