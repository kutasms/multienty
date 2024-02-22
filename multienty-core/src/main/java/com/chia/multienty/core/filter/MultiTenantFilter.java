package com.chia.multienty.core.filter;

import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.config.DefaultMultiTenantConfiguration;
import com.chia.multienty.core.domain.constants.MultiTenantHeaderConstants;
import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.TenantType;
import com.chia.multienty.core.domain.spi.TenantResourceMappingAlgorithm;
import com.chia.multienty.core.dubbo.service.DubboMultiTenantService;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.tenant.TenantDetailGetParameter;
import com.chia.multienty.core.pojo.Tenant;
import com.chia.multienty.core.service.TenantService;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.core.tools.TokenProvider;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MultiTenantFilter implements Filter {
    @Autowired
    private StringRedisService stringRedisService;
    @Autowired
    private DefaultMultiTenantConfiguration multiTenantConfiguration;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private TenantService tenantService;

    @Autowired
    private DubboMultiTenantService dubboMultiTenantService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String tenantId = req.getHeader(MultiTenantHeaderConstants.TENANT_ID_KEY);
        String appId = req.getHeader(MultiTenantHeaderConstants.APP_ID_KEY);
        String mppAppId = req.getHeader(MultiTenantHeaderConstants.MPP_APP_ID_KEY);
        if(null != tenantId) {
            Tenant tenant = new Tenant();
            TenantDTO tenantDTO = stringRedisService.get(tenant.getCacheKey(tenantId), TenantDTO.class);
            if (null != tenantDTO) {
                MultiTenantContext.setTenant(tenantDTO);
                TenantResourceMappingAlgorithm algorithm = multiTenantConfiguration
                        .getResourceMappingAlgorithmMap()
                        .get(TenantType.valueOf(tenantDTO.getType().intValue()).getConfigName());
                MultiTenantContext.setResourceMappingAlgorithm(algorithm);
            } else {
                tenantDTO = dubboMultiTenantService.getTenant(
                        new TenantDetailGetParameter().setTenantId(Long.parseLong(tenantId)));

                if(tenantDTO == null) {
                    throw new KutaRuntimeException(
                            HttpResultEnum.ILLEGAL_ARG_PATTERN,
                            MultiTenantHeaderConstants.TENANT_ID_KEY);
                }
                stringRedisService.setJson(tenant.getCacheKey(tenantId), tenantDTO);
                MultiTenantContext.setTenant(tenantDTO);
                TenantResourceMappingAlgorithm algorithm = multiTenantConfiguration
                        .getResourceMappingAlgorithmMap()
                        .get(TenantType.valueOf(tenantDTO.getType().intValue()).getConfigName());
                MultiTenantContext.setResourceMappingAlgorithm(algorithm);
            }
        }
        if(null != appId) {
            ApplicationType applicationType = ApplicationType.valueOf(Long.parseLong(appId));
            MultiTenantContext.setAppType(applicationType);
            String token = tokenProvider.getToken(req, applicationType.getTokenPrefix());
            if(Strings.isNotEmpty(token)) {
                MultiTenantContext.setToken(token);
            }
        }

        if(null != mppAppId) {
            MultiTenantContext.setMppAppId(mppAppId);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
