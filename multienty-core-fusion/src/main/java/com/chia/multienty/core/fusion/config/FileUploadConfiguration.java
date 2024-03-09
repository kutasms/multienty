package com.chia.multienty.core.fusion.config;

import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.strategy.file.FileUploadService;
import com.chia.multienty.core.strategy.file.impl.COSFileUploadServiceImpl;
import com.chia.multienty.core.strategy.file.impl.LocalFileUploadServiceImpl;
import com.chia.multienty.core.strategy.file.impl.OBSFileUploadServiceImpl;
import com.chia.multienty.core.strategy.file.impl.OSSFileUploadServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(YamlMultientyProperties.class)
@ConditionalOnProperty(prefix = "spring.multienty", name = "file")
public class FileUploadConfiguration implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Autowired
    private YamlMultientyProperties properties;
    @Bean
    @ConditionalOnMissingBean
    public FileUploadService fileUploadService() throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        FileUploadService service = null;
        switch (properties.getFile().getStorageModeEnum()) {
            case LOCAL:
                service = applicationContext.getAutowireCapableBeanFactory().createBean(LocalFileUploadServiceImpl.class);
                break;
            case COS:
                service = applicationContext.getAutowireCapableBeanFactory().createBean(COSFileUploadServiceImpl.class);
                break;
            case OBS:
                service = applicationContext.getAutowireCapableBeanFactory().createBean(OBSFileUploadServiceImpl.class);
                break;
            case OSS:
                service = applicationContext.getAutowireCapableBeanFactory().createBean(OSSFileUploadServiceImpl.class);
                break;
            case CUSTOM:
                Class<?> clazz = Class.forName(properties.getFile().getLocal().get("custom-file-upload-service-impl-class"));
                service = (FileUploadService)applicationContext.getAutowireCapableBeanFactory().createBean(clazz);
                break;
        }
        return service;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        FileUploadConfiguration.applicationContext = applicationContext;
    }
}
