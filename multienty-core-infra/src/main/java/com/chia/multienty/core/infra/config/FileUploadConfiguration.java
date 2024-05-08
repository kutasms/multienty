package com.chia.multienty.core.infra.config;

import com.chia.multienty.core.domain.enums.FileStorageMode;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.strategy.file.FileUploadService;
import com.chia.multienty.core.tools.MultientyServiceLoader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
@ConditionalOnClass(YamlMultientyProperties.class)
//@ConditionalOnProperty(prefix = "spring.multienty", name = "file")
public class FileUploadConfiguration implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Autowired
    private YamlMultientyProperties properties;
    @Bean
    @ConditionalOnMissingBean
    public FileUploadService fileUploadService() throws ClassNotFoundException{
        FileUploadService service = null;

        MultientyServiceLoader.register(FileUploadService.class);
        Collection<FileUploadService> instances = MultientyServiceLoader.getInstances(FileUploadService.class);
        FileStorageMode storageMode = FileStorageMode.valueOf(properties.getFile().getStorageMode());
        for (FileUploadService instance : instances) {
            if(instance.getMode().equals(storageMode)) {
                return instance;
            }
        }
        Class<?> clazz = Class.forName(properties.getFile().getLocal().get("custom-file-upload-service-impl-class"));
        service = (FileUploadService)applicationContext.getAutowireCapableBeanFactory().createBean(clazz);
        return service;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        FileUploadConfiguration.applicationContext = applicationContext;
    }
}
