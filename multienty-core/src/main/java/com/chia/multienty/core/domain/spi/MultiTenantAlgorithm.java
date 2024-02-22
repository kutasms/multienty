package com.chia.multienty.core.domain.spi;

import com.chia.multienty.core.domain.spi.lifecycle.InitializationProcessor;
import com.chia.multienty.core.domain.spi.typed.TypedSPI;

import java.util.Properties;

/**
 * 多租户相关算法
 */
public interface MultiTenantAlgorithm extends TypedSPI, InitializationProcessor {
    Properties getProps();
}
