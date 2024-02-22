package com.chia.multienty.core.sharding.listener;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.chia.multienty.core.sharding.tools.ShardingAlgorithmTool;
import com.chia.multienty.core.domain.constants.MultiTenantConstants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.spring.boot.datasource.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.concurrent.Executor;

@Slf4j
@Data
@Configuration
@ConditionalOnProperty(
        prefix = "spring.shardingsphere",
        name = "enabled",
        havingValue = "true")
public class NacosShardingConfigListener
{
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ConfigService configService;

    @Value("${spring.cloud.nacos.config.extension-configs[0].data-id}")
    private String dataId;

    @Value("${spring.cloud.nacos.config.extension-configs[0].group:DEFAULT_GROUP}")
    private String groupId;

    @Autowired
    private NacosConfigProperties nacosConfigProperties;


    @PostConstruct
    public void init() throws Exception {
        configService.addListener(dataId, groupId, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }
            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("[Multi Tenant Starter] start updating sharding config...");
                DynamicRoutingDataSource dynamicDs = (DynamicRoutingDataSource) dataSource;
                if(dynamicDs.getDataSources().containsKey(MultiTenantConstants.DS_SHARDING)) {
                    DataSource ds = dynamicDs.getDataSources().get(MultiTenantConstants.DS_SHARDING);
                    ShardingSphereDataSource shardingDS = (ShardingSphereDataSource) AopProxyUtils.getTarget(ds);
                    ShardingAlgorithmTool.refreshShardingConfig(shardingDS, configInfo, dataId, groupId);
                }
            }
        });
    }
}
