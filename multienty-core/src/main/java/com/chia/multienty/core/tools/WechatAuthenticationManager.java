package com.chia.multienty.core.tools;

import com.chia.multienty.core.domain.bo.WechatAuthentication;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.service.impl.DelegatingUserDetailsServiceImpl;
import com.chia.multienty.core.util.SpringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class WechatAuthenticationManager implements ReactiveAuthenticationManager {

    private final ReactiveUserDetailsService userDetailsService;


    private void check(Authentication authentication) {
        DelegatingUserDetailsServiceImpl delegatingUserDetailsService = SpringUtil.getBean(DelegatingUserDetailsServiceImpl.class);
        ApplicationType applicationType = delegatingUserDetailsService.getApplicationType();
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .doOnNext(this::check)
                .map(m -> m.getPrincipal().toString())
                .flatMap(userDetailsService::findByUsername)
                .map(this::createAuthenticationToken);
    }

    private WechatAuthentication createAuthenticationToken(UserDetails userDetails) {
        return new WechatAuthentication(userDetails, null, userDetails.getAuthorities());
    }
}
