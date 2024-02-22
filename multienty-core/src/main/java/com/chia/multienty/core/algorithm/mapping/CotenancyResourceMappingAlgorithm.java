package com.chia.multienty.core.algorithm.mapping;

import com.chia.multienty.core.domain.constants.ResourceMappingConstants;
import com.chia.multienty.core.domain.enums.FileStorageMode;
import com.chia.multienty.core.domain.enums.TenantType;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * 合租用户数据库映射算法
 */
public class CotenancyResourceMappingAlgorithm extends AbstractResourceMappingAlgorithm {

    @Override
    public FileStorageMode getFileUploadStrategy() {
        String strategy = props.getProperty(ResourceMappingConstants.FILE_UPLOAD_STRATEGY_KEY);

        if(StringUtils.isEmpty(strategy)) {
            return FileStorageMode.LOCAL;
        }
        return FileStorageMode.valueOf(strategy);
    }

    @Override
    public void init(Properties props) {
        this.props = props;
    }

    @Override
    public String getType() {
        return TenantType.COTENANCY.name();
    }
}
