package com.chia.multienty.spring.boot;

import com.chia.multienty.core.domain.config.DefaultMultiTenantConfiguration;
import com.chia.multienty.core.domain.spi.TenantResourceMappingAlgorithm;
import com.chia.multienty.core.filter.MultiTenantFilter;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantAlgorithmProperties;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.spring.boot.condition.MultiTenantSpringBootCondition;
import com.chia.multienty.spring.boot.registry.TenantResourceMappingAlgorithmBeanRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.Filter;
import java.util.Locale;
import java.util.Map;

@Configuration
@EnableConfigurationProperties({YamlMultiTenantProperties.class})
@ConditionalOnClass(YamlMultiTenantAlgorithmProperties.class)
@Conditional(MultiTenantSpringBootCondition.class)
@RequiredArgsConstructor
@Slf4j
public class MultiTenantSpringBootConfiguration implements ApplicationContextAware {

    @Autowired
    private YamlMultiTenantProperties multiTenantProperties;


    @Bean(name = "multiTenantConfiguration")
    public DefaultMultiTenantConfiguration multiTenantConfiguration(
            final ObjectProvider<Map<String, TenantResourceMappingAlgorithm>> tenantModeAlgorithmObjectProvider) {
        return DefaultMultiTenantConfiguration
                .builder()
                .resourceMappingAlgorithmMap(tenantModeAlgorithmObjectProvider.getIfAvailable())
                .build();
    }

    @Bean
    public MultiTenantFilter multiTenantFilter() {
        return new MultiTenantFilter();
    }

    @Bean
    public FilterRegistrationBean<MultiTenantFilter> multiTenantFilterRegistrationBean() {
        FilterRegistrationBean<MultiTenantFilter> tenantFilterRegisterBean = new FilterRegistrationBean<>();
        Builder<MultiTenantFilter> tenantFilterBuilder = new Builder<>(tenantFilterRegisterBean);
        tenantFilterBuilder.setConfig(multiTenantFilter(), 1, "/*");
        return tenantFilterRegisterBean;
    }

    @Bean
    public static TenantResourceMappingAlgorithmBeanRegistry tenantResourceMappingAlgorithmBeanRegistry(final Environment environment) {
        return new TenantResourceMappingAlgorithmBeanRegistry(environment);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.setContext(applicationContext);
    }


    private class Builder<T extends Filter>{
        private FilterRegistrationBean<T> filterRegistrationBean = null;

        public Builder(FilterRegistrationBean<T> filterRegistrationBean){
            this.filterRegistrationBean = filterRegistrationBean;
        }
        public Builder setConfig(Class<T> clazz, int order, String ...patterns){
            T filter = SpringUtil.getBeanOrCreate(clazz);
            // 设置过滤器
            this.filterRegistrationBean.setFilter(filter);
            // 设置启动顺序
            this.filterRegistrationBean.setOrder(order);
            String clazzPath = clazz.toString().toLowerCase(Locale.ROOT);
            // 配置过滤器的名称，首字母一定要小写，不然拦截了请求后会报错
            this.filterRegistrationBean.setName(clazzPath.substring(clazzPath.lastIndexOf(".")));
            // 配置拦截的请求地址
            this.filterRegistrationBean.addUrlPatterns(patterns);
            return this;
        }

        public Builder setConfig(T filter, int order, String... patterns) {
            this.filterRegistrationBean.setFilter(filter);
            this.filterRegistrationBean.setOrder(order);
            String clazzPath = filter.getClass().toString().toLowerCase(Locale.ROOT);
            this.filterRegistrationBean.setName(clazzPath.substring(clazzPath.lastIndexOf(".")));
            this.filterRegistrationBean.addUrlPatterns(patterns);
            return this;
        }

    }
}
