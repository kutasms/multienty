package com.chia.multienty.core.algorithm;

import com.chia.multienty.core.domain.spi.MultientyAlgorithm;
import com.chia.multienty.core.domain.spi.typed.TypedSPIRegistry;
import com.chia.multienty.core.properties.AlgorithmProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MultientyAlgorithmFactory {
    public static <T extends MultientyAlgorithm> T createAlgorithm(final AlgorithmProperties algorithmProperties,
                                                                     final Class<? extends MultientyAlgorithm> algorithmClazz) {

        return (T) TypedSPIRegistry.getRegisteredObject(algorithmClazz,
                algorithmProperties.getType(), algorithmProperties.getProps());
    }
}
