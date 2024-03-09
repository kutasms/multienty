package com.chia.multienty.oauth.handler;

import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DefaultSecurityContextRepository implements ServerSecurityContextRepository {

    private final TokenAuthenticationManager tokenAuthenticationManager;
    private final YamlMultientyProperties properties;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst(
                properties.getSecurity().getAuth().getHeader()
        );
        if (StringUtils.isNotEmpty(token)) {
            return tokenAuthenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(token, null)
            ).map(SecurityContextImpl::new);
        }
        return Mono.empty();
    }
}
