package com.chia.multienty.core.algorithm.mapping;

import com.chia.multienty.core.domain.constants.ResourceMappingConstants;
import com.chia.multienty.core.domain.enums.FileStorageMode;
import com.chia.multienty.core.domain.enums.TenantType;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * 企业级租户数据映射算法
 */
public final class EnterpriseTenantResourceMappingAlgorithm extends AbstractResourceMappingAlgorithm {

    @Override
    public FileStorageMode getFileUploadStrategy() {
        String strategy = props.getProperty(ResourceMappingConstants.FILE_UPLOAD_STRATEGY_KEY);

        if(StringUtils.isEmpty(strategy)) {
            return FileStorageMode.OSS;
        }
        return FileStorageMode.valueOf(strategy);
    }

    @Override
    public void init(Properties props) {
        this.props = props;
    }

    @Override
    public String getType() {
        return TenantType.ENTERPRISE.name();
    }
}
