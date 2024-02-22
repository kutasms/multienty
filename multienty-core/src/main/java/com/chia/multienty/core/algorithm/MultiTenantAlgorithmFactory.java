package com.chia.multienty.core.algorithm;

import com.chia.multienty.core.domain.spi.MultiTenantAlgorithm;
import com.chia.multienty.core.domain.spi.typed.TypedSPIRegistry;
import com.chia.multienty.core.properties.AlgorithmProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MultiTenantAlgorithmFactory {
    public static <T extends MultiTenantAlgorithm> T createAlgorithm(final AlgorithmProperties algorithmProperties,
                                                                     final Class<? extends MultiTenantAlgorithm> algorithmClazz) {

        return (T) TypedSPIRegistry.getRegisteredObject(algorithmClazz,
                algorithmProperties.getType(), algorithmProperties.getProps());
    }
}
