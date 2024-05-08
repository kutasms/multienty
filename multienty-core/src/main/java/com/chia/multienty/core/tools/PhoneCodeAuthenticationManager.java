package com.chia.multienty.core.tools;

import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.bo.PhoneCodeAuthentication;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.AuthenticationFailedException;
import com.chia.multienty.core.service.impl.DelegatingUserDetailsServiceImpl;
import com.chia.multienty.core.util.SpringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PhoneCodeAuthenticationManager implements ReactiveAuthenticationManager {

    private final ReactiveUserDetailsService userDetailsService;


    private void check(Authentication authentication) {
        DelegatingUserDetailsServiceImpl delegatingUserDetailsService = SpringUtil.getBean(DelegatingUserDetailsServiceImpl.class);
        ApplicationType applicationType = delegatingUserDetailsService.getApplicationType();
        String cacheKeyPrefix = null;
        switch (applicationType) {
            case MERCHANT:
                cacheKeyPrefix = MultientyConstants.TENANT_LOGIN_VERIFY_CODE_CACHE_KEY;
                break;
            case PLATFORM:
                cacheKeyPrefix = MultientyConstants.LOGIN_FAILURE_COUNT_CACHE_KEY;
                break;
        }
        Object objCachedVerCode = SpringUtil.getBean(StringRedisService.class).get(
                String.format(cacheKeyPrefix, authentication.getPrincipal().toString())
        );
        if(objCachedVerCode == null) {
            throw new AuthenticationFailedException(HttpResultEnum.VERIFICATION_CODE_EXPIRED);
        }
        if(!objCachedVerCode.toString().equals(authentication.getCredentials().toString())) {
            throw new AuthenticationFailedException(HttpResultEnum.VERIFICATION_CODE_ERROR);
        }
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .doOnNext(this::check)
                .map(m -> m.getPrincipal().toString())
                .flatMap(userDetailsService::findByUsername)
                .map(this::createAuthenticationToken);
    }

    private PhoneCodeAuthentication createAuthenticationToken(UserDetails userDetails) {
        return new PhoneCodeAuthentication(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
