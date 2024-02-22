package com.chia.multienty.core.domain.spi.lifecycle;

import java.util.Properties;

public interface InitializationProcessor {
    void init(Properties props);
}
