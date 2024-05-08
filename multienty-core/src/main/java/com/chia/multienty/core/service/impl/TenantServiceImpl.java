package com.chia.multienty.core.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.cache.redis.service.api.HashRedisService;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.basic.WebLogUser;
import com.chia.multienty.core.domain.bo.PhoneCodeAuthentication;
import com.chia.multienty.core.domain.constants.MultientyCacheKey;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.domain.dto.PublicKeyDTO;
import com.chia.multienty.core.domain.dto.TenantDTO;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.LoginMode;
import com.chia.multienty.core.domain.vo.DecryptedLoginInfo;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.domain.vo.LoginResult;
import com.chia.multienty.core.exception.AuthenticationFailedException;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.mapper.TenantMapper;
import com.chia.multienty.core.mybatis.KutaFuncEnum;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.Tenant;
import com.chia.multienty.core.pojo.TenantRole;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.*;
import com.chia.multienty.core.strategy.sms.SMSService;
import com.chia.multienty.core.strategy.sms.SMSServiceFactory;
import com.chia.multienty.core.strategy.sms.domain.SMSResult;
import com.chia.multienty.core.strategy.sms.domain.VerificationCodeBO;
import com.chia.multienty.core.tools.IdWorkerProvider;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.core.tools.TokenProvider;
import com.chia.multienty.core.util.*;
import com.github.yulichang.toolkit.MPJWrappers;
import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 租户信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Service
@RequiredArgsConstructor
public class TenantServiceImpl extends KutaBaseServiceImpl<TenantMapper, Tenant>
        implements TenantService, MultientyUserService {

    private final Optional<RedisTemplate> redisTemplate;

    private final TokenProvider tokenProvider;

    private final HashRedisService hashRedisService;

    private final StringRedisService stringRedisService;

    private final YamlMultientyProperties multientyProperties;


    private final TenantRoleService tenantRoleService;

    private final PermissionService permissionService;

    private final TenantSubAccountService tenantSubAccountService;

    private final ObjectProvider<Map<String,ReactiveAuthenticationManager>> authenticationManagerMap;

    @Autowired(required = false)
    private DelegatingUserDetailsServiceImpl delegatingUserDetailsService;
    /**
     * 通过令牌获取租户信息
     * @param token 令牌
     * */
    @Override
    public TenantDTO getByToken(String token) {
        Assert.isTrue(token != null);

        TenantDTO tenant = hashRedisService.hgetAll(token, TenantDTO.class);
        return tenant.getTenantId() != null ? tenant : null;
    }

    @Override
    public void refreshToken(String token) {
        redisTemplate.ifPresent(redisTemplate->
                redisTemplate.expire(token, multientyProperties.getSecurity().getAuth().getRenewDuration(), TimeUnit.SECONDS));
    }

    /**
     * 保存令牌
     * @param token 令牌
     * */
    @Override
    public void saveToken(String token, Long tenantId) {
        AssertUtil.checkNull(token, "令牌");
        AssertUtil.checkNull(tenantId, "租户编号");
        stringRedisService.setJson(token, tenantId.toString(), multientyProperties.getSecurity().getAuth().getAccessTokenExpired());
    }

    @Override
    public PublicKeyDTO getPublicKey() {
        return PublicKeyDTO
                .builder()
                .publicKey(multientyProperties.getSecurity().getRsa().getPublicKey())
                .mockServer(false)
                .build();
    }

    @Override
    public String createToken(TenantDTO tenant) {
        String token = tokenProvider.createToken(tenant.getTenantId(), tenant.getCompanyName(),
                Arrays.asList(tenant.getRoleAlias().split(",")));
        return token;
    }

    @Override
    @SneakyThrows
    public SMSResult sendVerificationCode(LoginVerificationCodeSendParameter parameter) {
        TenantDTO tenant = getByPhone(parameter.getPhoneNumber(), Tenant::getTenantId);
        if(tenant == null || tenant.getTenantId() == null) {
            throw new KutaRuntimeException(HttpResultEnum.UN_REGISTERED_PHONE_NUMBER);
        }
        SMSService service = SMSServiceFactory.getService();
        if(service != null) {
            VerificationCodeBO bo = new VerificationCodeBO();
            String code = RandomStringUtils.getRandomCode(4, 0);
            stringRedisService.set(
                    String.format(MultientyConstants.TENANT_LOGIN_VERIFY_CODE_CACHE_KEY, parameter.getPhoneNumber()),
                    code,
                    MultientyConstants.LOGIN_VERIFY_CODE_CACHE_MILLS);
            bo.setCode(code);
            bo.setTargetPhoneNumbers(Lists.newArrayList(parameter.getPhoneNumber()));
            return service.sendVerificationCode(bo);
        }else {
            throw new KutaRuntimeException(HttpResultEnum.PATTERN_SERVICE_UNAVAILABLE, "SMS");
        }
    }

    /**
     * 检测登录失败超出阈值范围
     */
    private void checkLoginFailureExceedingThreshold(DecryptedLoginInfo info) {
        String cacheKey = String.format(MultientyConstants.LOGIN_FAILURE_COUNT_CACHE_KEY,
                info.getLoginMode().getCode(), info.getUsername());
        Object objCount = stringRedisService.get(cacheKey);
        if(objCount == null) {
            stringRedisService.set(cacheKey, String.valueOf(1), 30 * 60 * 1000);
            return;
        }
        int count = Integer.parseInt(objCount.toString());
        Integer threshold = multientyProperties.getLoginFailureThreshold();
        if(count + 1 >= threshold) {
            throw new AuthenticationFailedException(HttpResultEnum.EXCEEDING_LOGIN_FAILURE_THRESHOLD);
        }
        stringRedisService.set(cacheKey, String.valueOf(count + 1), 30 * 60 * 1000);
    }

    private void clearLoginFailureExceedingCache(LoginMode loginMode, String username) {
        String cacheKey = String.format(MultientyConstants.LOGIN_FAILURE_COUNT_CACHE_KEY,
                loginMode.getCode(), username);
        stringRedisService.delete(cacheKey);
    }

    /**
     * 登录的账户名和密码经过RAS公钥加密
     */
    @Override
    public Mono<Result<LoginResult>> login(LoginParameter parameter) throws java.lang.Exception {
        String origStr = RsaUtil.decryptByPrivateKey(
                multientyProperties.getSecurity().getRsa().getPrivateKey(), parameter.getParam());
        DecryptedLoginInfo info = objectMapper.readValue(origStr, DecryptedLoginInfo.class);
        Mono<Authentication> authentication = null;
        checkLoginFailureExceedingThreshold(info);

        Authentication src = null;

        delegatingUserDetailsService.setApplicationType(ApplicationType.MERCHANT, info.getAccType());

        Optional<ReactiveAuthenticationManager> delegate = null;

        if(info.getLoginMode().equals(LoginMode.PHONE_CODE)) {
            delegate = ReactiveAuthenticationManagerUtil.getPhoneCodeAuthenticationManager(authenticationManagerMap);
        } else if(info.getLoginMode().equals(LoginMode.USERNAME_PASSWORD)) {
            delegate = ReactiveAuthenticationManagerUtil.getUsernamePasswordAuthenticationManager(authenticationManagerMap);
        }

        if(!delegate.isPresent()) {
            throw new KutaRuntimeException(500, "Please configure least one ReactiveAuthenticationManager.");
        }

        if(info.getLoginMode().equals(LoginMode.PHONE_CODE)) {
            src = new PhoneCodeAuthentication(info.getUsername(), info.getCode());
        } else if(info.getLoginMode().equals(LoginMode.USERNAME_PASSWORD)) {
            src = new UsernamePasswordAuthenticationToken(info.getUsername(), info.getPassword());
        }

        authentication = delegate.get().authenticate(src);

        return authentication.flatMap(auth-> {
            LoggedUserVO user = (LoggedUserVO) auth.getPrincipal();
            String token = tokenProvider.createToken(auth);
            parameter.setUserId(user.getLogUserId());
            WebLogUser.getInstance().setUserName(user.getName()).setUserId(user.getLogUserId());
            String cacheKey = String.format(MultientyCacheKey.PATTERN_LOGGED_TENANT, user.getLogUserId());
            redisTemplate.get().opsForValue().set(cacheKey, user, multientyProperties
                    .getSecurity()
                    .getAuth()
                    .getAccessTokenExpired(), TimeUnit.SECONDS);
            clearLoginFailureExceedingCache(info.getLoginMode(), info.getUsername());
            return Mono.just(new Result<>(LoginResult.builder()
                    .code(200)
                    .msg("success")
                    .accessToken(token)
                    .build()));
        });
    }


    @Override
    public TenantDTO getByPhone(String phone, SFunction<Tenant, ?>... columns) {
        return baseMapper.selectJoinOne(TenantDTO.class,
                new MTLambdaWrapper<Tenant>()
                        .select(columns)
                        .eq(Tenant::getPhoneNumber, phone)
        );
    }

    @Override
    public void logout(LogoutParameter parameter) {
        Long userId = tokenProvider.getUserId(parameter.getToken());
        redisTemplate.ifPresent(template-> template.delete(getCacheKey(userId)));
    }


    @Override
    public TenantDTO getDetail(TenantDetailGetParameter parameter) {
        return selectJoinOne(TenantDTO.class,
                MPJWrappers.<Tenant>lambdaJoin().eq(Tenant::getTenantId, parameter.getTenantId()));
    }

    @Override
    public IPage<TenantDTO> getPage(TenantPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), TenantDTO.class,
                new MTLambdaWrapper<Tenant>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getTenantIds()), Tenant::getTenantId, parameter.getTenantIds())
        );
    }

    @Override
    public void save(TenantSaveParameter parameter) {
        parameter.setPassword(MD5Util.md5WithSalt(parameter.getPassword()));
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(parameter, tenant);
        tenant.setTenantId(IdWorkerProvider.next());
        tenant.setTenantNo(RandomStringUtils.getRandomCode(6, 6));
        tenant.setExpired(false);
        tenant.setLocked(false);
        try {
            saveTE(tenant);
        }
        catch (Exception ex) {
            if(ex instanceof DuplicateKeyException) {
                throw new KutaRuntimeException(HttpResultEnum.DUPLICATE_USERNAME);
            }
        }
        parameter.setTenantId(tenant.getTenantId());
    }

    @Override
    public void update(TenantUpdateParameter parameter) {
        Tenant tenant = new Tenant();
        if(parameter.getPassword().length() < 20) {
            parameter.setPassword(MD5Util.md5WithSalt(parameter.getPassword()));
        }
        BeanUtils.copyProperties(parameter, tenant);
        updateByIdTE(tenant);
    }

    @Override
    public void delete(TenantDeleteParameter parameter) {
        removeByIdTE(parameter.getTenantId());
    }

    @Override
    public LoggedUserVO getLoggedInfo() throws KutaRuntimeException, IOException {

        String token = MultientyContext.getToken();
        Claims claims = tokenProvider.getClaims(token);
        String userId = claims.getSubject();
        String cacheKey = String.format(MultientyCacheKey.PATTERN_LOGGED_TENANT, userId);
        LoggedUserVO loggedUserVO = stringRedisService.get(cacheKey, LoggedUserVO.class);
        if(loggedUserVO != null) {
            return loggedUserVO;
        }

        if(!loggedUserVO.getIsMainAcc()) {
            return tenantSubAccountService.getLoggedUser(Long.parseLong(userId));
        }

        Tenant tenant = getOne(mtLambdaWrapper().eq(Tenant::getTenantId, Long.parseLong(userId)));
        if(tenant.getAvatar() == null) {
            tenant.setAvatar(MultientyConstants.DEFAULT_AVATAR);
        }
        List<Role> roles = tenantRoleService.getRoles(tenant.getTenantId());
        return LoggedUserVO
                .builder()
                .username(tenant.getCompanyName())
                .avatar(tenant.getAvatar())
                .permissions(permissionService
                        .getFuncPermissions(
                                new FuncPermissionListGetParameter()
                                        .setRoles(roles)
                                        .setOwner(ApplicationType.MERCHANT.getValue())
                        )
                )
                .roles(roles.stream().map(m->m.getName()).collect(Collectors.toList()))
                .superAdmin(roles
                        .stream()
                        .filter(p->p.getSuperAdmin() != null && p.getSuperAdmin())
                        .findAny() != null)
                .build();
    }

    @Override
    public UserDetails findByUsername(String username) {
        TenantDTO tenant = selectJoinOne(
                TenantDTO.class,
                new MTLambdaWrapper<Tenant>()
                        .selectAll(Tenant.class)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT, Role::getName, TenantDTO::getRoleName)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT,Role::getAlias, TenantDTO::getRoleAlias)
                        .leftJoin(TenantRole.class, TenantRole::getTenantId,
                                Tenant::getTenantId)
                        .leftJoin(Role.class, Role::getRoleId,
                                TenantRole::getRoleId)
                        .and(and-> and.eq(Tenant::getUsername, username).or(or->or.eq(Tenant::getPhoneNumber, username)))
        );

        if(tenant == null) {
            throw new KutaRuntimeException(HttpResultEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        List<Role> roles = tenantRoleService.getRoles(tenant.getTenantId());
        if(roles.size() > 0) {
            tenant.setPermissions(permissionService.getFuncPermissions(
                    new FuncPermissionListGetParameter()
                            .setOwner(ApplicationType.PLATFORM.getValue())
                            .setRoles(roles)
            ));
        }
        tenant.setSuperAdmin(true);
        if(tenant.getAvatar() == null) {
            tenant.setAvatar(MultientyConstants.DEFAULT_AVATAR);
        }
        LoggedUserVO loggedUser = new LoggedUserVO(tenant);
        loggedUser.setApplicationType(ApplicationType.MERCHANT);
        return loggedUser;
    }
}
