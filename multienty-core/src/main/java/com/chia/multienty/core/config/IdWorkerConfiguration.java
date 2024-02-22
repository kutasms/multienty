package com.chia.multienty.core.config;

import com.chia.multienty.core.tools.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class IdWorkerConfiguration {
    @Value("${spring.kuta.snowflake.worker-id:none}")
    private String workId;
    @Value("${spring.kuta.snowflake.data-center-id:none}")
    private String dataCenterId;

    @Value("${spring.application.name}")
    private String appName;

    @Value(("${server.port}"))
    private Long port;

    @Bean
    @Primary
    public SnowflakeIdWorker idWorker() {
        return new SnowflakeIdWorker(getWorkId(), getDataCenterId());
    }

    private Long getWorkId() {
        if("none".equals(workId)) {
            return getDefaultWorkId();
        } else {
            return Long.parseLong(workId);
        }
    }

    private Long getDefaultWorkId() {
        int[] ints = StringUtils.toCodePoints(appName);
        int sums = 0;
        for (int b : ints) {
            sums += b;
        }
        return (long) (sums % 32);
    }

    private Long getDataCenterId() {
        if("none".equals(dataCenterId)) {
            return getDefaultDataCenterId();
        } else {
            return Long.parseLong(dataCenterId);
        }
    }

    private Long getDefaultDataCenterId() {
        return port % 32;
    }
}
