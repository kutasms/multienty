package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.LoginAccountType;
import com.chia.multienty.core.dubbo.service.DubboWxOAuthService;
import com.chia.multienty.core.service.MultientyUserService;
import com.chia.multienty.core.service.TenantService;
import com.chia.multienty.core.service.TenantSubAccountService;
import com.chia.multienty.core.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class DelegatingUserDetailsServiceImpl implements ReactiveUserDetailsService, InitializingBean {

    private static Map<String, MultientyUserService> multientyUserServiceMap;

    private static ThreadLocal<ApplicationType> applicationTypeThreadLocal = new ThreadLocal<>();

    private static ThreadLocal<LoginAccountType> accountTypeThreadLocal = new ThreadLocal<>();

    @Autowired
    private TenantService tenantService;
    @Autowired
    private UserService userService;

    @Autowired
    private TenantSubAccountService tenantSubAccountService;

    @Autowired(required = false)
    private DubboWxOAuthService dubboWxOAuthService;

    private final String MERCHANT_SUB_ACC_SUFFIX = "_SUB_ACC";


    public void setApplicationType(ApplicationType applicationType) {
        applicationTypeThreadLocal.set(applicationType);
    }

    public void setApplicationType(ApplicationType applicationType, LoginAccountType accountType) {
        applicationTypeThreadLocal.set(applicationType);
        accountTypeThreadLocal.set(accountType);
        if(applicationType.equals(ApplicationType.MERCHANT) && accountType.equals(LoginAccountType.SUB_ACCOUNT)) {
            multientyUserServiceMap.put(ApplicationType.MERCHANT.name() + MERCHANT_SUB_ACC_SUFFIX,  tenantSubAccountService);
        }
    }

    public ApplicationType getApplicationType() {
        return applicationTypeThreadLocal.get();
    }

    public LoginAccountType getAccountType() {
        return accountTypeThreadLocal.get();
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        if(applicationTypeThreadLocal.get().equals(ApplicationType.MERCHANT)) {
            LoginAccountType accountType = accountTypeThreadLocal.get();
            if(accountType != null && accountType.equals(LoginAccountType.SUB_ACCOUNT)) {
                return Mono.just(multientyUserServiceMap.get(ApplicationType.MERCHANT.name() + MERCHANT_SUB_ACC_SUFFIX).findByUsername(username));
            }
        }
       return Mono.just(multientyUserServiceMap.get(applicationTypeThreadLocal.get().name()).findByUsername(username));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationTypeThreadLocal.set(ApplicationType.PLATFORM);
        multientyUserServiceMap = new HashMap<>();
        multientyUserServiceMap.put(ApplicationType.MERCHANT.name(), tenantService);
        multientyUserServiceMap.put(ApplicationType.PLATFORM.name(), userService);
        multientyUserServiceMap.put(ApplicationType.WECHAT_MPP.name(), dubboWxOAuthService);
    }
}
