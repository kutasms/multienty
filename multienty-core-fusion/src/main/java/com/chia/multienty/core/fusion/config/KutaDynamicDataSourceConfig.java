package com.chia.multienty.core.fusion.config;

import com.alibaba.nacos.api.exception.NacosException;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.provider.AbstractDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.properties.ShardingSphereExtendProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Configuration(proxyBeanMethods =false)
@DependsOn("dynamicDataSourceProperties")
@EnableConfigurationProperties(ShardingSphereExtendProperties.class)
@ConditionalOnProperty(prefix = DynamicDataSourceProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@AutoConfigureBefore({DynamicDataSourceAutoConfiguration.class, SpringBootConfiguration.class})
public class KutaDynamicDataSourceConfig {
    @Lazy
    @Resource(name = "shardingSphereDataSource")
    private DataSource shardingSphereDataSource;
    @Resource
    private DynamicDataSourceProperties dynamicDataSourceProperties;
    @Autowired
    private ShardingSphereExtendProperties shardingSphereExtendProperties;
    @Autowired
    private DefaultDataSourceCreator defaultDataSourceCreator;
    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider() throws NacosException {
        Map<String, DataSourceProperty> datasourceMap = dynamicDataSourceProperties.getDatasource();

        return new AbstractDataSourceProvider(defaultDataSourceCreator) {
            @Override
            public Map<String, DataSource> loadDataSources() {
                Map<String, DataSource> dataSourceMap = createDataSourceMap(datasourceMap);
                if(shardingSphereExtendProperties.getEnabled()) {
                    dataSourceMap.put(MultientyConstants.DS_SHARDING, shardingSphereDataSource);
                }
                return dataSourceMap;
            }
        };
    }


    /**
     * 将动态数据源设置为首选的
     * 当spring存在多个数据源时, 自动注入的是首选的对象
     * 设置为主要的数据源之后，就可以支持shardingJdbc原生的配置方式了
     */
    @Primary
    @Bean
    public DataSource dataSource() throws NacosException {
        List<DynamicDataSourceProvider> providers = new ArrayList<>();
        providers.add(dynamicDataSourceProvider());
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource(providers);
        dataSource.setPrimary(dynamicDataSourceProperties.getPrimary());
        dataSource.setStrict(dynamicDataSourceProperties.getStrict());
        dataSource.setStrategy(dynamicDataSourceProperties.getStrategy());
        dataSource.setP6spy(dynamicDataSourceProperties.getP6spy());
        dataSource.setSeata(dynamicDataSourceProperties.getSeata());
        return dataSource;
    }

}
