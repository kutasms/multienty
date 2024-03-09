package com.chia.multienty.spring.boot;

import com.chia.multienty.core.domain.config.DefaultMultientyConfiguration;
import com.chia.multienty.core.domain.spi.TenantResourceMappingAlgorithm;
import com.chia.multienty.core.fusion.filter.MultientyFilter;
import com.chia.multienty.core.properties.yaml.YamlMultientyAlgorithmProperties;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.util.SpringUtil;
import com.chia.multienty.spring.boot.condition.MultientySpringBootCondition;
import com.chia.multienty.spring.boot.registry.SMSServiceBeanRegistry;
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
@EnableConfigurationProperties({YamlMultientyProperties.class})
@ConditionalOnClass(YamlMultientyAlgorithmProperties.class)
@Conditional(MultientySpringBootCondition.class)
@RequiredArgsConstructor
@Slf4j
public class MultientySpringBootConfiguration implements ApplicationContextAware {

    @Autowired
    private YamlMultientyProperties multientyProperties;


    @Bean(name = "multientyConfiguration")
    public DefaultMultientyConfiguration multientyConfiguration(
            final ObjectProvider<Map<String, TenantResourceMappingAlgorithm>> tenantModeAlgorithmObjectProvider) {
        return DefaultMultientyConfiguration
                .builder()
                .resourceMappingAlgorithmMap(tenantModeAlgorithmObjectProvider.getIfAvailable())
                .build();
    }

    @Bean
    public MultientyFilter multientyFilter() {
        return new MultientyFilter();
    }

    @Bean
    public FilterRegistrationBean<MultientyFilter> multientyFilterRegistrationBean() {
        FilterRegistrationBean<MultientyFilter> tenantFilterRegisterBean = new FilterRegistrationBean<>();
        Builder<MultientyFilter> tenantFilterBuilder = new Builder<>(tenantFilterRegisterBean);
        tenantFilterBuilder.setConfig(multientyFilter(), 1, "/*");
        return tenantFilterRegisterBean;
    }

    @Bean
    public static TenantResourceMappingAlgorithmBeanRegistry tenantResourceMappingAlgorithmBeanRegistry(final Environment environment) {
        return new TenantResourceMappingAlgorithmBeanRegistry(environment);
    }

    @Bean
    public static SMSServiceBeanRegistry smsServiceBeanRegistry() {
        return new SMSServiceBeanRegistry();
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
