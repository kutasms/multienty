package com.chia.multienty.core.domain.config;

import com.chia.multienty.core.domain.spi.TenantResourceMappingAlgorithm;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class DefaultMultiTenantConfiguration implements MultiTenantConfiguration{
    private Map<String, TenantResourceMappingAlgorithm> resourceMappingAlgorithmMap;

}
