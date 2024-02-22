package com.chia.multienty.core.service.impl;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.chia.multienty.core.cache.redis.service.api.HashRedisService;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.basic.WebLogUser;
import com.chia.multienty.core.domain.bo.UserStatusChangedNotifyBO;
import com.chia.multienty.core.domain.constants.KutaRabbitMQConstants;
import com.chia.multienty.core.domain.constants.MultiTenantConstants;
import com.chia.multienty.core.domain.constants.UserBoType;
import com.chia.multienty.core.domain.dto.PublicKeyDTO;
import com.chia.multienty.core.domain.dto.UserDTO;
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
import com.chia.multienty.core.mybatis.KutaLambdaWrapper;
import com.chia.multienty.core.mybatis.service.impl.KutaBaseServiceImpl;
import com.chia.multienty.core.parameter.user.*;
import com.chia.multienty.core.pojo.Role;
import com.chia.multienty.core.pojo.User;
import com.chia.multienty.core.pojo.UserRole;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.rabbitmq.ImmediateMessageProducer;
import com.chia.multienty.core.service.PermissionService;
import com.chia.multienty.core.service.UserRoleService;
import com.chia.multienty.core.service.UserService;
import com.chia.multienty.core.strategy.sms.SMSService;
import com.chia.multienty.core.strategy.sms.domain.SMSResult;
import com.chia.multienty.core.strategy.sms.domain.VerificationCodeBO;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.core.tools.TokenProvider;
import com.chia.multienty.core.util.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
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
public class UserServiceImpl extends KutaBaseServiceImpl<UserMapper, User> implements UserService {

    private final PermissionService kutaPermissionService;

    private final Optional<RedisTemplate> redisTemplate;

    private final TokenProvider tokenProvider;

    private final HashRedisService hashRedisService;

    private final StringRedisService stringRedisService;

    private final YamlMultiTenantProperties multiTenantProperties;

    private final UserRoleService userRoleService;

    private final Optional<ImmediateMessageProducer> immediateMessageProducer;

    private final Optional<SMSService> smsServiceOptional;

    /**
     * 通过令牌获取客户信息
     * @param token 令牌
     * */
    @Override
    public UserDTO getByToken(String token) {
        Assert.isTrue(token != null);

        UserDTO user = hashRedisService.hgetAll(token, UserDTO.class);
        return user.getUserId() != null ? user : null;
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
    public void saveToken(String token, UserDTO user) {
        AssertUtil.checkNull(token, "令牌");
        AssertUtil.checkNull(user, "用户");
        Map<String, Object> map = KutaBeanUtil.bean2Map(user);
        hashRedisService.hmset(token, map, multiTenantProperties.getSecurity().getAuth().getTokenHoldingTime());
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
    public String createToken(String username, UserDTO user) {
        String token = tokenProvider.createToken(user.getUserId(), username, user.getCreateTime().toString());
        return token;
    }

    @Override
    @SneakyThrows
    public SMSResult sendVerificationCode(LoginVerificationCodeSendParameter parameter) {
        UserDTO user = getByPhone(parameter.getPhoneNumber(), User::getUserId);
        if(user == null || user.getUserId() == null) {
            throw new KutaRuntimeException(HttpResultEnum.UN_REGISTERED_PHONE_NUMBER);
        }
        if(smsServiceOptional.isPresent()) {
            VerificationCodeBO bo = new VerificationCodeBO();
            String code = RandomStringUtils.getRandomCode(4, 0);
            stringRedisService.set(
                    String.format(MultiTenantConstants.LOGIN_VERIFY_CODE_CACHE_KEY, parameter.getPhoneNumber()),
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

        checkLoginFailureExceedingThreshold(info);

        if(info.getLoginMode().equals(LoginMode.PHONE_CODE)) {
            Object objCachedVerCode = stringRedisService.get(
                    String.format(MultiTenantConstants.LOGIN_VERIFY_CODE_CACHE_KEY, info.getUsername())
            );
            if(objCachedVerCode == null) {
                throw new AuthenticationFailedException(HttpResultEnum.VERIFICATION_CODE_EXPIRED);
            }
            if(!objCachedVerCode.toString().equals(info.getCode())) {
                throw new AuthenticationFailedException(HttpResultEnum.VERIFICATION_CODE_ERROR);
            }
        }

        UserDTO user = selectJoinOne(
                UserDTO.class,
                new KutaLambdaWrapper<User>()
                        .selectAll(User.class)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT, Role::getName, UserDTO::getRoleName)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT,Role::getAlias, UserDTO::getRoleAlias)
                        .leftJoin(UserRole.class, UserRole::getUserId,
                                User::getUserId)
                        .leftJoin(Role.class, Role::getRoleId,
                                UserRole::getRoleId)
                        .eq(info.getLoginMode().equals(LoginMode.USERNAME_PASSWORD),User::getUsername, info.getUsername())
                        .eq(info.getLoginMode().equals(LoginMode.PHONE_CODE), User::getPhone, info.getUsername())
                        .groupBy(User::getUserId)
        );
        if(info.getLoginMode().equals(LoginMode.USERNAME_PASSWORD)) {
            if(user == null || !MD5Util.verify(info.getPassword(), user.getPassword())) {
                //密码验证不通过
                throw new AuthenticationFailedException(HttpResultEnum.USER_NAME_OR_PASSWORD_ERROR);
            }
        }

        String token = createToken(info.getUsername(), user);
        user.setToken(token);
        saveToken(token, user);
        updateByIdWithVersion(user);

        parameter.setUserId(user.getUserId());
        WebLogUser.getInstance().setUserName(user.getName()).setUserId(user.getUserId());
        return LoginResult.builder().accessToken(token).build();
    }

    @Override
    public void logout(LogoutParameter parameter) {
        UserDTO user = getByToken(parameter.getToken());
        if(user != null) {
            parameter.setUserId(user.getUserId());
        }
        redisTemplate.ifPresent(template-> template.delete(parameter.getToken()));
    }

    @Override
    public UserDTO getDetail(Long userId) {
        return baseMapper.selectJoinOne(UserDTO.class,
                new KutaLambdaWrapper<User>()
                        .eq(User::getUserId, userId));
    }

    @Override
    public LoggedUserVO getUserInfo() throws KutaRuntimeException {
        UserDTO cachedUser = getByToken(MultiTenantContext.getToken());
        if(cachedUser == null) {
            throw new KutaRuntimeException(HttpResultEnum.TOKEN_EXPIRED);
        }

        User user = getBy(cachedUser.getUserId(),
                User::getName,
                User::getAvatar);
        return LoggedUserVO
                .builder()
                .username(user.getName())
                .avatar(user.getAvatar())
                .roles(userRoleService
                        .getRoleNames(cachedUser.getUserId()))
                .permissions(kutaPermissionService
                        .getFuncPermissions(
                                new FuncPermissionListGetParameter())
                        .stream()
                        .map(m -> m.getComponent())
                        .collect(Collectors.toList())
                )
                .superAdmin(userRoleService
                        .getRoles(
                                cachedUser.getUserId())
                        .stream()
                        .filter(p->p.getSuperAdmin() != null && p.getSuperAdmin())
                        .findAny() != null)
                .build();
    }





    @Override
    public UserDTO getByPhone(String phone, SFunction<User, ?>... columns) {
        return baseMapper.selectJoinOne(UserDTO.class,
                new KutaLambdaWrapper<User>()
                        .select(columns)
                        .eq(User::getPhone, phone)
        );
    }

    @Override
    public IPage<UserDTO> getList(UserListGetParameter parameter) {
        return baseMapper.selectJoinPage(parameter.getPageObj(),
                UserDTO.class,
                new MPJLambdaWrapper<User>()
                        .selectAll(User.class)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT, Role::getName, UserDTO::getRoleName)
                        .selectFunc(KutaFuncEnum.GROUP_CONCAT, Role::getRoleId, UserDTO::getRoleIdStrings)
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
            Long count = userRoleService.selectJoinCount(new KutaLambdaWrapper<UserRole>()
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


}
