package com.chia.multienty.core.service.impl;

import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.service.MultientyUserService;
import com.chia.multienty.core.service.TenantService;
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

    private static Map<ApplicationType, MultientyUserService> multientyUserServiceMap;

    private static ThreadLocal<ApplicationType> applicationTypeThreadLocal = new ThreadLocal<>();

    @Autowired
    private TenantService tenantService;
    @Autowired
    private UserService userService;


    public void setApplicationType(ApplicationType applicationType) {
        applicationTypeThreadLocal.set(applicationType);
    }

    public ApplicationType getApplicationType() {
        return applicationTypeThreadLocal.get();
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
       return multientyUserServiceMap.get(applicationTypeThreadLocal.get()).findByUsername(username);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationTypeThreadLocal.set(ApplicationType.PLATFORM);
        multientyUserServiceMap = new HashMap<>();
        multientyUserServiceMap.put(ApplicationType.TENANT, tenantService);
        multientyUserServiceMap.put(ApplicationType.PLATFORM, userService);
    }
}
