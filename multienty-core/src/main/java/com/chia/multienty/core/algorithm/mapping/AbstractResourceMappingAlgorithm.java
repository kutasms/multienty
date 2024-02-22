package com.chia.multienty.core.algorithm.mapping;

import com.chia.multienty.core.domain.constants.ResourceMappingConstants;
import com.chia.multienty.core.domain.enums.FileStorageMode;
import com.chia.multienty.core.domain.spi.TenantResourceMappingAlgorithm;
import com.chia.multienty.core.strategy.file.FileUploadService;
import com.chia.multienty.core.tools.MultiTenantServiceLoader;
import com.chia.multienty.core.util.SpringUtil;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Collection;
import java.util.Properties;

public abstract class AbstractResourceMappingAlgorithm implements TenantResourceMappingAlgorithm {

    @Getter
    protected Properties props;

    @Override
    public boolean useStandaloneDatabase() {
        return Boolean.valueOf(props.getOrDefault(ResourceMappingConstants.USE_STANDALONE_DATABASE_KEY,
                false).toString());
    }

    @Override
    public boolean isDivideTableByDate() {
        return Boolean.valueOf(props.getOrDefault(ResourceMappingConstants.DIVIDE_TABLE_BY_DATE_KEY,
                false).toString());
    }


    @SneakyThrows
    public FileUploadService getFileUploadService() {
        FileStorageMode strategy = getFileUploadStrategy();
        MultiTenantServiceLoader.register(FileUploadService.class);
        Collection<FileUploadService> instances = MultiTenantServiceLoader.getInstances(FileUploadService.class);
        FileUploadService service = null;
        if(strategy.equals(FileStorageMode.CUSTOM)) {
            String className = props.getProperty("impl-class");
            service = SpringUtil.getBeanOrCreate(className);
        } else {
            service = instances.stream().filter(p -> p.getMode().equals(strategy)).findAny().orElse(null);
        }
        return service;
    }
}
