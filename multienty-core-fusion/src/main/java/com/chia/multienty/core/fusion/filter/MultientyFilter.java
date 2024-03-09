package com.chia.multienty.core.fusion.filter;

import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.config.DefaultMultientyConfiguration;
import com.chia.multienty.core.domain.constants.MultientyHeaderConstants;
import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.TenantType;
import com.chia.multienty.core.domain.spi.TenantResourceMappingAlgorithm;
import com.chia.multienty.core.dubbo.service.DubboMultientyService;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.tenant.TenantDetailGetParameter;
import com.chia.multienty.core.pojo.Tenant;
import com.chia.multienty.core.service.TenantService;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.core.tools.TokenProvider;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MultientyFilter implements Filter {
    @Autowired
    private StringRedisService stringRedisService;
    @Autowired
    private DefaultMultientyConfiguration multientyConfiguration;
    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private TenantService tenantService;

    @Autowired
    private DubboMultientyService dubboMultientyService;

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
        String tenantId = req.getHeader(MultientyHeaderConstants.TENANT_ID_KEY);
        String appId = req.getHeader(MultientyHeaderConstants.APP_ID_KEY);
        String mppAppId = req.getHeader(MultientyHeaderConstants.MPP_APP_ID_KEY);
        String token = req.getHeader(MultientyHeaderConstants.TOKEN_KEY);
        if(null != tenantId) {
            Tenant tenant = new Tenant();
            TenantDTO tenantDTO = stringRedisService.get(tenant.getCacheKey(tenantId), TenantDTO.class);
            if (null != tenantDTO) {
                MultientyContext.setTenant(tenantDTO);
                TenantResourceMappingAlgorithm algorithm = multientyConfiguration
                        .getResourceMappingAlgorithmMap()
                        .get(TenantType.valueOf(tenantDTO.getType().intValue()).getConfigName());
                MultientyContext.setResourceMappingAlgorithm(algorithm);
            } else {
                tenantDTO = dubboMultientyService.getTenant(
                        new TenantDetailGetParameter().setTenantId(Long.parseLong(tenantId)));

                if(tenantDTO == null) {
                    throw new KutaRuntimeException(
                            HttpResultEnum.ILLEGAL_ARG_PATTERN,
                            MultientyHeaderConstants.TENANT_ID_KEY);
                }
                stringRedisService.setJson(tenant.getCacheKey(tenantId), tenantDTO);
                MultientyContext.setTenant(tenantDTO);
                TenantResourceMappingAlgorithm algorithm = multientyConfiguration
                        .getResourceMappingAlgorithmMap()
                        .get(TenantType.valueOf(tenantDTO.getType().intValue()).getConfigName());
                MultientyContext.setResourceMappingAlgorithm(algorithm);
            }
        }
        if(null != appId) {
            ApplicationType applicationType = ApplicationType.valueOf(Long.parseLong(appId));
            MultientyContext.setAppType(applicationType);
            if(Strings.isNotEmpty(token)) {
                MultientyContext.setToken(token);
            }
        }

        if(null != mppAppId) {
            MultientyContext.setMppAppId(mppAppId);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
