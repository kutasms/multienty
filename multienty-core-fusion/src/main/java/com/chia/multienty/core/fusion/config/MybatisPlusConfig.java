package com.chia.multienty.core.fusion.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.chia.multienty.core.handler.KutaMetaObjectHandler;
import com.chia.multienty.core.mybatis.KutaMybatisPlusMySqlDialect;
import com.chia.multienty.core.mybatis.KutaSqlInjector;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * mybatis-plus配置类
 */
@Configuration
@Slf4j
public class MybatisPlusConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MybatisPlusProperties properties;


    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();


    @Autowired(required = false)
    private Interceptor[] interceptors;

    @Autowired(required = false)
    private DatabaseIdProvider databaseIdProvider;

    @Autowired
    private KutaMetaObjectHandler kutaMetaObjectHandler;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        PaginationInnerInterceptor page = new PaginationInnerInterceptor();
        page.setDialect(new KutaMybatisPlusMySqlDialect());
        mybatisPlusInterceptor.addInnerInterceptor(page);
        log.info("注册mybatis-plus拦截器");
        return mybatisPlusInterceptor;
    }

    /**
     * 这里全部使用mybatis-autoconfigure 已经自动加载的资源。不手动指定
     * 配置文件和mybatis-boot的配置文件同步
     * @return
     */
    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(dataSource);
        mybatisPlus.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            mybatisPlus.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        //mybatisPlus.setConfiguration(properties.getConfiguration());
        List<Interceptor> interceptorList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            interceptorList.addAll(Arrays.stream(this.interceptors).collect(Collectors.toList()));
        }
        interceptorList.add(mybatisPlusInterceptor());
        Interceptor[] array = new Interceptor[0];
        mybatisPlus.setPlugins(interceptorList.toArray(array));
        MybatisConfiguration mc = new MybatisConfiguration();
        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        mybatisPlus.setConfiguration(mc);
        if (this.databaseIdProvider != null) {
            mybatisPlus.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            mybatisPlus.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            mybatisPlus.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations());
        }

        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        globalConfig.setDbConfig(dbConfig);
        globalConfig.setMetaObjectHandler(kutaMetaObjectHandler);
        globalConfig.setSqlInjector(new KutaSqlInjector());
        mybatisPlus.setGlobalConfig(globalConfig);
        log.info("MYBATIS PLUS 配置完成");
        return mybatisPlus;
    }

}
