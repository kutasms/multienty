package com.chia.multienty.core.service.impl;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.cache.redis.service.api.HashRedisService;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.basic.WebLogUser;
import com.chia.multienty.core.domain.constants.MultiTenantConstants;
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
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.tenant.*;
import com.chia.multienty.core.parameter.user.FuncPermissionListGetParameter;
import com.chia.multienty.core.parameter.user.LoginParameter;
import com.chia.multienty.core.parameter.user.LoginVerificationCodeSendParameter;
import com.chia.multienty.core.parameter.user.LogoutParameter;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.Tenant;
import com.chia.multienty.core.pojo.TenantRole;
import com.chia.multienty.core.pojo.User;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.service.PermissionService;
import com.chia.multienty.core.service.TenantRoleService;
import com.chia.multienty.core.service.TenantService;
import com.chia.multienty.core.strategy.sms.SMSService;
import com.chia.multienty.core.strategy.sms.domain.SMSResult;
import com.chia.multienty.core.strategy.sms.domain.VerificationCodeBO;
import com.chia.multienty.core.tools.IdWorkerProvider;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.core.tools.TokenProvider;
import com.chia.multienty.core.util.AssertUtil;
import com.chia.multienty.core.util.ListUtil;
import com.chia.multienty.core.util.RandomStringUtils;
import com.chia.multienty.core.util.RsaUtil;
import com.github.yulichang.toolkit.MPJWrappers;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
public class TenantServiceImpl extends KutaBaseServiceImpl<TenantMapper, Tenant> implements TenantService {

    private final Optional<RedisTemplate> redisTemplate;

    private final TokenProvider tokenProvider;

    private final HashRedisService hashRedisService;

    private final StringRedisService stringRedisService;

    private final YamlMultiTenantProperties multiTenantProperties;

    private final Optional<SMSService> smsServiceOptional;

    private final TenantRoleService tenantRoleService;

    private final PermissionService permissionService;


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
                redisTemplate.expire(token, multiTenantProperties.getSecurity().getAuth().getRenewTime(), TimeUnit.SECONDS));
    }

    /**
     * 保存令牌
     * @param token 令牌
     * */
    @Override
    public void saveToken(String token, Long tenantId) {
        AssertUtil.checkNull(token, "令牌");
        AssertUtil.checkNull(tenantId, "租户编号");
        stringRedisService.setJson(token, tenantId, multiTenantProperties.getSecurity().getAuth().getTokenHoldingTime());
    }

    @Override
    public PublicKeyDTO getPublicKey() {
        return PublicKeyDTO
                .builder()
                .publicKey(multiTenantProperties.getSecurity().getRsa().getPublicKey())
                .mockServer(false)
                .build();
    }

    @Override
    public String createToken(TenantDTO tenant) {
        String token = tokenProvider.createToken(tenant.getTenantId(), tenant.getCompanyName(), tenant.getCreateTime().toString());
        return token;
    }

    @Override
    @SneakyThrows
    public SMSResult sendVerificationCode(LoginVerificationCodeSendParameter parameter) {
        TenantDTO tenant = getByPhone(parameter.getPhoneNumber(), Tenant::getTenantId);
        if(tenant == null || tenant.getTenantId() == null) {
            throw new KutaRuntimeException(HttpResultEnum.UN_REGISTERED_PHONE_NUMBER);
        }
        if(smsServiceOptional.isPresent()) {
            VerificationCodeBO bo = new VerificationCodeBO();
            String code = RandomStringUtils.getRandomCode(4, 0);
            stringRedisService.set(
                    String.format(MultiTenantConstants.TENANT_LOGIN_VERIFY_CODE_CACHE_KEY, parameter.getPhoneNumber()),
                    code,
                    MultiTenantConstants.LOGIN_VERIFY_CODE_CACHE_MILLS);
            bo.setCode(code);
            bo.setTargetPhoneNumbers(Lists.newArrayList(parameter.getPhoneNumber()));
            return smsServiceOptional.get().sendVerificationCode(new VerificationCodeBO());
        }
        return null;
    }

    /**
     * 检测登录失败超出阈值范围
     */
    private void checkLoginFailureExceedingThreshold(DecryptedLoginInfo info) {
        String cacheKey = String.format(MultiTenantConstants.LOGIN_FAILURE_COUNT_CACHE_KEY,
                info.getLoginMode().getCode(), info.getUsername());
        Object objCount = stringRedisService.get(cacheKey);
        if(objCount == null) {
            stringRedisService.set(cacheKey, 1);
            return;
        }
        int count = Integer.parseInt(objCount.toString());
        Integer threshold = multiTenantProperties.getLoginFailureThreshold();
        if(count + 1 >= threshold) {
            throw new AuthenticationFailedException(HttpResultEnum.EXCEEDING_LOGIN_FAILURE_THRESHOLD);
        }
        stringRedisService.set(cacheKey, count + 1);
    }

    /**
     * 登录的账户名和密码经过RAS公钥加密
     * */
    @Override
    public LoginResult login(LoginParameter parameter) throws java.lang.Exception {
        String origStr = RsaUtil.decryptByPrivateKey(
                multiTenantProperties.getSecurity().getRsa().getPrivateKey(), parameter.getParam());
        DecryptedLoginInfo info = JSONObject.parseObject(origStr, DecryptedLoginInfo.class);
        if(!info.getAppId().equals(ApplicationType.TENANT.getValue())) {
            throw new AuthenticationFailedException(HttpResultEnum.LOGIN_APPLICATION_FAILURE);
        }
        checkLoginFailureExceedingThreshold(info);

        if(!info.getLoginMode().equals(LoginMode.PHONE_CODE)) {
            throw new AuthenticationFailedException(HttpResultEnum.DISALLOWED_LOGIN_MODE);
        }

        Object objCachedVerCode = stringRedisService.get(
                String.format(MultiTenantConstants.LOGIN_VERIFY_CODE_CACHE_KEY, info.getUsername())
        );
        if(objCachedVerCode == null) {
            throw new AuthenticationFailedException(HttpResultEnum.VERIFICATION_CODE_EXPIRED);
        }
        if(!objCachedVerCode.toString().equals(info.getCode())) {
            throw new AuthenticationFailedException(HttpResultEnum.VERIFICATION_CODE_ERROR);
        }

        TenantDTO tenant = selectJoinOne(
                TenantDTO.class,
                new KutaLambdaWrapper<Tenant>()
                        .selectAll(Tenant.class)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT, Role::getName, TenantDTO::getRoleName)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT,Role::getAlias, TenantDTO::getRoleAlias)
                        .leftJoin(TenantRole.class, TenantRole::getTenantId,
                                Tenant::getTenantId)
                        .leftJoin(Role.class, Role::getRoleId,
                                TenantRole::getRoleId)
                        .eq(Tenant::getPhoneNumber, info.getUsername())
                        .groupBy(User::getUserId)
        );


        String token = createToken(tenant);
        tenant.setToken(token);
        saveToken(token, tenant.getTenantId());
        updateByIdWithVersion(tenant);

        parameter.setUserId(tenant.getTenantId());
        WebLogUser.getInstance().setUserName(tenant.getCompanyName()).setUserId(tenant.getTenantId());
        return LoginResult.builder().accessToken(token).build();
    }


    @Override
    public TenantDTO getByPhone(String phone, SFunction<Tenant, ?>... columns) {
        return baseMapper.selectJoinOne(TenantDTO.class,
                new KutaLambdaWrapper<Tenant>()
                        .select(columns)
                        .eq(Tenant::getPhoneNumber, phone)
        );
    }

    @Override
    public void logout(LogoutParameter parameter) {
        TenantDTO user = getByToken(parameter.getToken());
        if(user != null) {
            parameter.setUserId(user.getTenantId());
        }
        redisTemplate.ifPresent(template-> template.delete(parameter.getToken()));
    }


    @Override
    public TenantDTO getDetail(TenantDetailGetParameter parameter) {
        return selectJoinOne(TenantDTO.class,
                MPJWrappers.<Tenant>lambdaJoin().eq(Tenant::getTenantId, parameter.getTenantId()));
    }

    @Override
    public IPage<TenantDTO> getPage(TenantPageGetParameter parameter) {
        return selectJoinListPage(parameter.getPageObj(), TenantDTO.class,
                new KutaLambdaWrapper<Tenant>()
                        .solveGenericParameters(parameter)
                        .in(!ListUtil.isEmpty(parameter.getTenantIds()), Tenant::getTenantId, parameter.getTenantIds())
        );
    }

    @Override
    public void save(TenantSaveParameter parameter) {
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(parameter, tenant);
        tenant.setTenantId(IdWorkerProvider.next());
        saveTE(tenant);
        parameter.setTenantId(tenant.getTenantId());
    }

    @Override
    public void update(TenantUpdateParameter parameter) {
        Tenant tenant = new Tenant();
        BeanUtils.copyProperties(parameter, tenant);
        updateByIdTE(tenant);
    }

    @Override
    public void delete(TenantDeleteParameter parameter) {
        removeByIdTE(parameter.getTenantId());
    }

    @Override
    public LoggedUserVO getLoggedInfo() throws KutaRuntimeException {
        TenantDTO cachedTenant = getByToken(MultiTenantContext.getToken());
        if(cachedTenant == null) {
            throw new KutaRuntimeException(HttpResultEnum.TOKEN_EXPIRED);
        }

        Tenant tenant = getBy(cachedTenant.getTenantId(),
                Tenant::getCompanyName,
                Tenant::getAvatar);
        return LoggedUserVO
                .builder()
                .username(tenant.getCompanyName())
                .avatar(tenant.getAvatar())
                .roles(tenantRoleService
                        .getRoleNames(cachedTenant.getUserId()))
                .permissions(permissionService
                        .getFuncPermissions(
                                new FuncPermissionListGetParameter())
                        .stream()
                        .map(m -> m.getComponent())
                        .collect(Collectors.toList())
                )
                .superAdmin(tenantRoleService
                        .getRoles(
                                cachedTenant.getTenantId())
                        .stream()
                        .filter(p->p.getSuperAdmin() != null && p.getSuperAdmin())
                        .findAny() != null)
                .build();
    }
}
