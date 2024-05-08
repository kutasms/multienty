package com.chia.multienty.core.infra.flyway.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.chia.multienty.core.infra.flyway.condition.FlywayExtensionCondition;
import com.chia.multienty.core.infra.flyway.proerties.FlywayExtensionProperties;
import com.chia.multienty.core.infra.flyway.util.FlywayUtil;
import com.chia.multienty.core.infra.sharding.tools.ShardingAlgorithmTool;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.util.SpringUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@EnableConfigurationProperties(FlywayExtensionProperties.class)
@ConditionalOnClass({
        FlywayExtensionProperties.class,
        DataSource.class,
        YamlMultientyProperties.class
})
@Conditional(FlywayExtensionCondition.class)
@Configuration
@EnableTransactionManagement
public class MultientyFlywayConfiguration implements ApplicationContextAware {
    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private FlywayExtensionProperties properties;

    @Autowired
    private YamlMultientyProperties yamlMultientyProperties;
    @Autowired
    private DataSource dataSource;

    @Bean
    @SneakyThrows
    public void migrate() {
        log.info(">>>>>>>>> Use flyway to migrate each data source in the dynamic data source ");
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        FlywayUtil.migrateDynamicDataSource(ds);
        ShardingAlgorithmTool.postAfterStarted();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.setContext(applicationContext);
    }
}
