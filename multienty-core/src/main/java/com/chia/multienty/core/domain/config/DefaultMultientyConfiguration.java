package com.chia.multienty.core.domain.config;

import com.chia.multienty.core.domain.spi.TenantResourceMappingAlgorithm;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class DefaultMultientyConfiguration implements MultientyConfiguration{
    private Map<String, TenantResourceMappingAlgorithm> resourceMappingAlgorithmMap;

}
