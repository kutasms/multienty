package com.chia.multienty.core.domain.spi;

import com.chia.multienty.core.domain.enums.FileStorageMode;
import com.chia.multienty.core.strategy.file.FileUploadService;

public interface TenantResourceMappingAlgorithm extends MultientyAlgorithm {

    boolean useStandaloneDatabase();

    boolean isDivideTableByDate();

    FileStorageMode getFileUploadStrategy();

    FileUploadService getFileUploadService();
}
