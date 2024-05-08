package com.chia.multienty.core.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.cache.redis.service.api.HashRedisService;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.basic.WebLogUser;
import com.chia.multienty.core.domain.bo.UserStatusChangedNotifyBO;
import com.chia.multienty.core.domain.constants.KutaRabbitMQConstants;
import com.chia.multienty.core.domain.constants.MultientyCacheKey;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.domain.constants.UserBoType;
import com.chia.multienty.core.domain.dto.PublicKeyDTO;
import com.chia.multienty.core.domain.dto.UserDTO;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.enums.LoginMode;
import com.chia.multienty.core.domain.enums.StatusEnum;
import com.chia.multienty.core.domain.vo.DecryptedLoginInfo;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.domain.vo.LoginResult;
import com.chia.multienty.core.exception.AuthenticationFailedException;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.mapper.UserMapper;
import com.chia.multienty.core.mybatis.KutaFuncEnum;
import com.chia.multienty.core.mybatis.MTLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.User;
import com.chia.multienty.core.pojo.UserRole;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.rabbitmq.ImmediateMessageProducer;
import com.chia.multienty.core.service.MultientyUserService;
import com.chia.multienty.core.service.PermissionService;
import com.chia.multienty.core.service.UserRoleService;
import com.chia.multienty.core.service.UserService;
import com.chia.multienty.core.strategy.sms.SMSService;
import com.chia.multienty.core.strategy.sms.SMSServiceFactory;
import com.chia.multienty.core.strategy.sms.domain.SMSResult;
import com.chia.multienty.core.strategy.sms.domain.VerificationCodeBO;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.core.tools.TokenProvider;
import com.chia.multienty.core.util.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 管理账户信息 服务实现类
 * </p>
 *
 * @author Multi Tenant Auto Generator
 * @since 2024-01-03
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Primary
public class UserServiceImpl extends KutaBaseServiceImpl<UserMapper, User> implements UserService, MultientyUserService {

    private final PermissionService kutaPermissionService;

    private final Optional<RedisTemplate> redisTemplate;

    private final TokenProvider tokenProvider;

    private final HashRedisService hashRedisService;

    private final StringRedisService stringRedisService;

    private final YamlMultientyProperties multientyProperties;

    private final UserRoleService userRoleService;

    private final Optional<ImmediateMessageProducer> immediateMessageProducer;
    private final ObjectProvider<Map<String,ReactiveAuthenticationManager>> authenticationManagerMap;
    private final PasswordEncoder passwordEncoder;

    private final String DEFAULT_AVATAR = "https://kutashop.cn/img/chia.png";

    @Autowired(required = false)
    private DelegatingUserDetailsServiceImpl delegatingUserDetailsService;


    @Override
    public PublicKeyDTO getPublicKey() {
        return PublicKeyDTO
                .builder()
                .publicKey(multientyProperties.getSecurity().getRsa().getPublicKey())
                .mockServer(false)
                .build();
    }

    @Override
    @SneakyThrows
    public SMSResult sendVerificationCode(LoginVerificationCodeSendParameter parameter) {
        LoggedUserVO user = getByPhone(parameter.getPhoneNumber(), User::getUserId);
        if(user == null || user.getUserId() == null) {
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

        checkLoginFailureExceedingThreshold(info);
        delegatingUserDetailsService.setApplicationType(ApplicationType.PLATFORM);
        Mono<Authentication> authentication = null;
        if(info.getLoginMode().equals(LoginMode.PHONE_CODE)) {
            Object objCachedVerCode = stringRedisService.get(
                    String.format(MultientyConstants.LOGIN_VERIFY_CODE_CACHE_KEY, info.getUsername())
            );
            if(objCachedVerCode == null) {
                throw new AuthenticationFailedException(HttpResultEnum.VERIFICATION_CODE_EXPIRED);
            }
            if(!objCachedVerCode.toString().equals(info.getCode())) {
                throw new AuthenticationFailedException(HttpResultEnum.VERIFICATION_CODE_ERROR);
            }

            UserDTO user = selectJoinOne(
                    UserDTO.class,
                    new MTLambdaWrapper<User>()
                            .selectAll(User.class)
                            .selectFunc(KutaFuncEnum.GROUP_CONCAT, Role::getName, UserDTO::getRoleName)
                            .selectFunc(KutaFuncEnum.GROUP_CONCAT,Role::getAlias, UserDTO::getRoleAlias)
                            .leftJoin(UserRole.class, UserRole::getUserId,
                                    User::getUserId)
                            .leftJoin(Role.class, Role::getRoleId,
                                    UserRole::getRoleId)
                            .eq(User::getPhone, info.getUsername())
                            .groupBy(User::getUserId)
            );
        }

        if(info.getLoginMode().equals(LoginMode.USERNAME_PASSWORD)) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(info.getUsername(), info.getPassword());

            Optional<ReactiveAuthenticationManager> authenticationManagerOptional =
                    ReactiveAuthenticationManagerUtil.getUsernamePasswordAuthenticationManager(authenticationManagerMap);

            if(authenticationManagerOptional.isPresent()) {
                authentication = authenticationManagerOptional.get().authenticate(authenticationToken);
            }
            else {
                throw new KutaRuntimeException(HttpResultEnum.SYSTEM_ERROR);
            }
            if (Objects.isNull(authentication)) {
                throw new KutaRuntimeException(HttpResultEnum.TOKEN_APPROVE_ERROR);
            }
        }

        return authentication.flatMap(auth -> {
            String token = tokenProvider.createToken(auth);
            com.chia.multienty.core.domain.vo.LoggedUserVO user = (com.chia.multienty.core.domain.vo.LoggedUserVO) auth.getPrincipal();
            log.info("当前账号对应的token是: {}",token);
            parameter.setUserId(user.getLogUserId());
            WebLogUser.getInstance().setUserName(user.getName()).setUserId(user.getLogUserId());
            String cacheKey = String.format(MultientyCacheKey.PATTERN_LOGGED_USER, user.getLogUserId());
            clearLoginFailureExceedingCache(info.getLoginMode(), info.getUsername());
            redisTemplate.get().opsForValue().set(cacheKey, user, multientyProperties
                    .getSecurity()
                    .getAuth()
                    .getAccessTokenExpired(), TimeUnit.SECONDS);
            return Mono.just(new Result<>(LoginResult.builder().code(200).msg("登录成功").accessToken(token).build()));
        });
    }


    @Override
    public void logout(LogoutParameter parameter) {
        Claims claims = tokenProvider.getClaims(parameter.getToken());
        Long userId = tokenProvider.getUserId(claims);
        stringRedisService.delete(getCacheKey(userId));
    }

    @Override
    public UserDTO getDetail(Long userId) {
        return baseMapper.selectJoinOne(UserDTO.class,
                new MTLambdaWrapper<User>()
                        .eq(User::getUserId, userId));
    }
    @Override
    public LoggedUserVO getUserInfo() throws IOException {
        String token = MultientyContext.getToken();
        Claims claims = tokenProvider.getClaims(token);
        String userId = claims.getSubject();
        return getUserInfo(Long.parseLong(userId), null);
    }


    @Override
    public LoggedUserVO getUserInfo(Long userId, UserDTO userDTO) throws KutaRuntimeException, IOException {
        String cacheKey = String.format(MultientyCacheKey.PATTERN_LOGGED_USER, userId);
        LoggedUserVO loggedUserVO = stringRedisService.get(cacheKey, com.chia.multienty.core.domain.vo.LoggedUserVO.class);

        if(loggedUserVO != null) {
            return loggedUserVO;
        }

        if(userDTO == null) {
            userDTO = getDetail(userId);
        }

        List<Role> roles = userRoleService.getRoles(userDTO.getUserId());
        return LoggedUserVO
                .builder()
                .userId(userId)
                .enabled(userDTO.getStatus().equals(StatusEnum.NORMAL.getCode()))
                .expired(userDTO.getExpired())
                .locked(userDTO.getLocked())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .username(userDTO.getUsername())
                .name(userDTO.getName())
                .avatar(userDTO.getAvatar())
                .roles(roles.stream().map(m->m.getName()).collect(Collectors.toList()))
                .permissions(kutaPermissionService
                        .getFuncPermissions(
                                new FuncPermissionListGetParameter()
                                        .setOwner(ApplicationType.PLATFORM.getValue())
                                        .setRoles(roles)
                        )
                )
                .superAdmin(roles.stream().filter(p->p.getSuperAdmin() != null && p.getSuperAdmin())
                        .findAny().isPresent())
                .build();
    }

    @Override
    public LoggedUserVO getByPhone(String phone, SFunction<User, ?>... columns) {
        return baseMapper.selectJoinOne(LoggedUserVO.class,
                new MTLambdaWrapper<User>()
                        .select(columns)
                        .eq(User::getPhone, phone)
        );
    }

    @Override
    public IPage<LoggedUserVO> getList(UserListGetParameter parameter) {
        return baseMapper.selectJoinPage(parameter.getPageObj(),
                LoggedUserVO.class,
                new MPJLambdaWrapper<User>()
                        .selectAll(User.class)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT, Role::getName, LoggedUserVO::getJoiningRoles)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT, Role::getRoleId, LoggedUserVO::getJoinedRoleIds)
                        .selectFunc(KutaFuncEnum.CASE_ADMIN, Role::getSuperAdmin)
                        .leftJoin(UserRole.class, UserRole::getUserId, User::getUserId)
                        .leftJoin(Role.class,Role::getRoleId, UserRole::getRoleId)
                        .gt(!ArrayUtils.isEmpty(parameter.getCreateTimeDuration()) && parameter.getCreateTimeDuration()[0] != null,
                                User::getCreateTime, parameter.getCreateTimeDuration()[0])
                        .lt(!ArrayUtils.isEmpty(parameter.getCreateTimeDuration()) && parameter.getCreateTimeDuration()[1]!=null,
                                User::getCreateTime, parameter.getCreateTimeDuration()[1])
                        .and(!StringUtils.isEmpty(parameter.getKeywords()),
                                ew-> ew.like(User::getName, parameter.getKeywords())
                                        .or().likeLeft(User::getPhone, parameter.getKeywords())
                                        .or().like(User::getUsername, parameter.getKeywords()))
                        .notIn(User::getStatus, StatusEnum.DELETED.getCode())
                        .eq(!StringUtils.isEmpty(parameter.getStatus()), User::getStatus, parameter.getStatus())
                        .in(!ListUtil.isEmpty(parameter.getStatusList()),User::getStatus, parameter.getStatusList())
                        .orderByDesc(parameter.getOrderByDescColumns() != null, parameter.getOrderByDescColumns())
                        .orderByAsc(parameter.getOrderByAscColumns()!=null, parameter.getOrderByAscColumns())
                        .groupBy(User::getUserId)
        );
    }



    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = {KutaRuntimeException.class, java.lang.Exception.class})
    public int update(UserUpdateParameter parameter) {
        User user = new User();
        BeanUtils.copyProperties(parameter, user);
        if(parameter.getRole()!=null) {
            userRoleService.change(parameter.getUserId(), parameter.getRole().getRoleId());
        }
        if(parameter.getRoleIds() != null) {
            userRoleService.setRoles(parameter.getUserId(), parameter.getRoleIds());
        }
        user.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(user);
    }

    @Override
    @Transactional(isolation= Isolation.DEFAULT,propagation= Propagation.REQUIRED,rollbackFor = {KutaRuntimeException.class, java.lang.Exception.class})
    public int save(UserSaveParameter parameter) {
        User user = new User();
        BeanUtils.copyProperties(parameter, user);
        user.setPassword(MD5Util.md5WithSalt(user.getPassword()));
        user.setStatus(StatusEnum.NORMAL.getCode());
        user.setCreateTime(LocalDateTime.now());
        user.setVersion(1L);
        if(user.getAvatar() == null) {
            user.setAvatar(DEFAULT_AVATAR);
        }
        int result = baseMapper.insert(user);
        if(parameter.getRole() != null) {
            UserRole userRole = new UserRole().setUserId(user.getUserId()).setRoleId(parameter.getRole().getRoleId());
            userRoleService.save(userRole);
        }
        if(parameter.getRoleIds() != null) {
            userRoleService.setRoles(user.getUserId(), parameter.getRoleIds());
        }
        parameter.setUserId(user.getUserId());
        return result;
    }

    @Override
    public int deleteSafely(Long platformUserId) throws KutaRuntimeException {
        Assert.notNull(platformUserId);
        User user = getBy(platformUserId,
                User::getUserId,
                User::getStatus,
                User::getUsername,
                User::getVersion);
        List<Role> roles = userRoleService.getRoles(platformUserId);
        boolean isSuperAdmin = roles.stream().filter(p->p.getSuperAdmin()!=null && p.getSuperAdmin()).findAny()!=null;
        if(isSuperAdmin) {
            Long count = userRoleService.selectJoinCount(new MTLambdaWrapper<UserRole>()
                    .innerJoin(Role.class, Role::getRoleId, UserRole::getRoleId)
                    .eq(Role::getSuperAdmin, true)
            );
            if(count.intValue() < 1) {
                throw new KutaRuntimeException(HttpResultEnum.LEAST_ONE_SUPER_ADMIN);
            }
        }
        user.setStatus(StatusEnum.DELETED.getCode());
        return baseMapper.updateById(user);
    }

    @Override
    public boolean disable(Long userId, Long version) {
        return setStatus(userId, version, StatusEnum.DISABLED);
    }

    @Override
    public boolean disable(Long userId) {
        Long version = getBy(userId, User::getVersion).getVersion();
        return disable(userId, version);
    }


    private boolean setStatus(Long userId, Long version, StatusEnum status) {
        User user = new User();
        user.setUserId(userId);
        user.setVersion(version);
        user.setStatus(status.getCode());
        user.setUpdateTime(LocalDateTime.now());
        updateByIdWithVersion(user);
        //通知其他端状态更新
        immediateMessageProducer.ifPresent(producer-> {
            producer.send(new UserStatusChangedNotifyBO()
                            .setUserId(userId)
                            .setStatus(status.getCode()),
                    KutaRabbitMQConstants.USER_NOTIFY_ROUTING_KEY,
                    UserBoType.STATUS_CHANGED.name(),
                    userId
            );
        });

        return true;
    }


    @Override
    public boolean enable(Long userId, Long version) {
        return setStatus(userId, version, StatusEnum.NORMAL);
    }
    @Override
    public boolean enable(Long userId) {
        Long version = getBy(userId, User::getVersion).getVersion();
        return enable(userId, version);
    }



    @Override
    public UserDetails findByUsername(String username) {
        UserDTO user = selectJoinOne(
                UserDTO.class,
                new MTLambdaWrapper<User>()
                        .selectAll(User.class)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT, Role::getName, UserDTO::getRoleName)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT,Role::getAlias, UserDTO::getRoleAlias)
                        .leftJoin(UserRole.class, UserRole::getUserId,
                                User::getUserId)
                        .leftJoin(Role.class, Role::getRoleId,
                                UserRole::getRoleId)
                        .eq(User::getUsername, username)
        );

        if(user == null) {
            throw new KutaRuntimeException(HttpResultEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        List<Role> roles = userRoleService.getRoles(user.getUserId());

        user.setPermissions(kutaPermissionService.getFuncPermissions(
                new FuncPermissionListGetParameter()
                        .setOwner(ApplicationType.PLATFORM.getValue())
                        .setRoles(roles)
        ));

        user.setSuperAdmin(roles.stream().filter(p->p.getSuperAdmin() != null && p.getSuperAdmin())
                .findAny().isPresent());

        com.chia.multienty.core.domain.vo.LoggedUserVO loggedUser = new com.chia.multienty.core.domain.vo.LoggedUserVO(user);
        loggedUser.setApplicationType(ApplicationType.PLATFORM);
        return loggedUser;
    }
}
