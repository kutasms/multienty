package com.chia.multienty.core.infra.filter;

import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.config.DefaultMultientyConfiguration;
import com.chia.multienty.core.domain.constants.MultientyCacheKey;
import com.chia.multienty.core.domain.constants.MultientyHeaderConstants;
import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.TenantType;
import com.chia.multienty.core.domain.spi.TenantResourceMappingAlgorithm;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.dubbo.service.DubboMultientyService;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.parameter.tenant.TenantDetailGetParameter;
import com.chia.multienty.core.pojo.Tenant;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.core.tools.TokenProvider;
import com.chia.multienty.core.util.SpringUtil;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.Objects;

@Activate(group = CommonConstants.PROVIDER)
public class MultientyDubboProviderFilter implements Filter {
    private YamlMultientyProperties properties;
    private TokenProvider tokenProvider;
    private StringRedisService stringRedisService;
    private DefaultMultientyConfiguration multientyConfiguration;
    private DubboMultientyService dubboMultientyService;
    @Override
    @SneakyThrows
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String tenantId = invocation.getAttachment(MultientyHeaderConstants.TENANT_ID_KEY);
        String appId = invocation.getAttachment(MultientyHeaderConstants.APP_ID_KEY);
        String mppAppId = invocation.getAttachment(MultientyHeaderConstants.MPP_APP_ID_KEY);
        String token = invocation.getAttachment(MultientyHeaderConstants.TOKEN_KEY);
        String programId = invocation.getAttachment(MultientyHeaderConstants.PROGRAM_ID_KEY);
        if(properties == null) {
            properties = SpringUtil.getBean(YamlMultientyProperties.class);
        }
        if(properties.getIsDemo()) {
            MultientyContext.setTestAcc(true);
        }

        if(!Objects.isNull(token)) {
            MultientyContext.setToken(token);
            if(tokenProvider == null) {
                tokenProvider = SpringUtil.getBean(TokenProvider.class);
            }
            Claims claims = tokenProvider.getClaims(token);
            Long userId = Long.valueOf(claims.getSubject());
            ApplicationType appType = ApplicationType.valueOf(Long.parseLong(appId));
            String userCacheKey = null;
            switch (appType) {
                case PLATFORM:
                    userCacheKey = String.format(MultientyCacheKey.PATTERN_LOGGED_USER, userId);
                    break;
                case MERCHANT:
                    userCacheKey = String.format(MultientyCacheKey.PATTERN_LOGGED_TENANT, userId);
                    break;
                case WECHAT_MPP:
                    userCacheKey = String.format(MultientyCacheKey.PATTERN_LOGGED_WECHAT_MPP, userId);
                    break;
                default:
                    throw new NotImplementedException("This application type has not been implemented");
            }
            if(stringRedisService == null) {
                stringRedisService = SpringUtil.getBean(StringRedisService.class);
            }
            LoggedUserVO loggedUserVO = stringRedisService.get(userCacheKey, LoggedUserVO.class);
            MultientyContext.setUser(loggedUserVO);
        }
        if(null != tenantId) {
            Tenant tenant = new Tenant();
            if(stringRedisService == null) {
                stringRedisService = SpringUtil.getBean(StringRedisService.class);
            }
            TenantDTO tenantDTO = stringRedisService.get(tenant.getCacheKey(tenantId), TenantDTO.class);
            if (null != tenantDTO) {
                MultientyContext.setTenant(tenantDTO);
                if(multientyConfiguration == null) {
                    multientyConfiguration = SpringUtil.getBean(DefaultMultientyConfiguration.class);
                }
                TenantResourceMappingAlgorithm algorithm = multientyConfiguration
                        .getResourceMappingAlgorithmMap()
                        .get(TenantType.valueOf(tenantDTO.getType().intValue()).getConfigName());
                MultientyContext.setResourceMappingAlgorithm(algorithm);
            } else {
                if(dubboMultientyService == null) {
                    dubboMultientyService = SpringUtil.getBean(DubboMultientyService.class);
                }
                tenantDTO = dubboMultientyService.getTenant(
                        new TenantDetailGetParameter().setTenantId(Long.parseLong(tenantId)));
                if(tenantDTO == null) {
                    throw new KutaRuntimeException(
                            HttpResultEnum.ILLEGAL_ARG_PATTERN,
                            MultientyHeaderConstants.TENANT_ID_KEY);
                }
                if(stringRedisService == null) {
                    stringRedisService = SpringUtil.getBean(StringRedisService.class);
                }
                stringRedisService.setJson(tenant.getCacheKey(tenantId), tenantDTO);
                MultientyContext.setTenant(tenantDTO);
                if(multientyConfiguration == null) {
                    multientyConfiguration = SpringUtil.getBean(DefaultMultientyConfiguration.class);
                }
                TenantResourceMappingAlgorithm algorithm = multientyConfiguration
                        .getResourceMappingAlgorithmMap()
                        .get(TenantType.valueOf(tenantDTO.getType().intValue()).getConfigName());
                MultientyContext.setResourceMappingAlgorithm(algorithm);
            }
        }
        if(null != appId) {
            ApplicationType applicationType = ApplicationType.valueOf(Long.parseLong(appId));
            MultientyContext.setAppType(applicationType);
        }

        if(null != mppAppId) {
            MultientyContext.setMppAppId(mppAppId);
        }
        if(null != programId) {
            MultientyContext.setProgramId(Long.parseLong(programId));
        }
        Result result = invoker.invoke(invocation);
        return result;
    }

}
