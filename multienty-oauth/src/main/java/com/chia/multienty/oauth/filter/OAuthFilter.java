package com.chia.multienty.oauth.filter;

import com.chia.multienty.core.domain.constants.MultientyHeaderConstants;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.service.impl.DelegatingUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class OAuthFilter implements WebFilter {
    @Autowired
    private DelegatingUserDetailsServiceImpl delegatingUserDetailsService;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String appId = exchange.getRequest().getHeaders().getFirst(MultientyHeaderConstants.APP_ID_KEY);
        if(appId == null) {
            return Mono.error(new KutaRuntimeException(HttpResultEnum.HEADER_LOSE_PATTERN, MultientyHeaderConstants.APP_ID_KEY));
        }
        ApplicationType applicationType = ApplicationType.valueOf(Long.parseLong(appId));
        delegatingUserDetailsService.setApplicationType(applicationType);
        return chain.filter(exchange);
    }
}
