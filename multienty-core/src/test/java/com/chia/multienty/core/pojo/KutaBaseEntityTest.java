package com.chia.multienty.core.pojo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
class KutaBaseEntityTest {

    @Test
    public void testGetCacheKey() {
        Tenant tenant = new Tenant();
        tenant.setTenantId(2947819382L);
        log.info("tenant cache key:{}", tenant.getCacheKey(tenant.getTenantId()));
    }
}