package com.chia.multienty.oauth.handler;

import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultientyCacheKey;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.tools.TokenProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class DefaultAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final StringRedisService stringRedisService;

    private final TokenProvider tokenProvider;

    private final ObjectMapper objectMapper;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        return authentication.map(auth -> {
            ServerWebExchange exchange = authorizationContext.getExchange();
            ServerHttpRequest request = exchange.getRequest();
            LoggedUserVO loggedUserVO = (LoggedUserVO) auth.getPrincipal();
            try {
                loggedUserVO = stringRedisService.get(String.format(MultientyCacheKey.PATTERN_LOGGED_USER,
                        loggedUserVO.getUserId()), LoggedUserVO.class);
                List<String> apis = loggedUserVO.getPermissions()
                        .stream()
                        .filter(p-> StringUtils.hasText(p.getApi()))
                        .map(m -> m.getApi())
                        .collect(Collectors.toList());
                Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
                String path = request.getURI().getPath();

                for (String api : apis) {
                    // TODO
                    // 查询用户访问所需角色进行对比
                    if (antPathMatcher.match(api, path)) {
                        log.info(String.format("用户请求API校验通过，GrantedAuthority:{%s}  Path:{%s} ", api, path));
                        return new AuthorizationDecision(true);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return new AuthorizationDecision(false);
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }

    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return check(authentication, object)
                .filter(AuthorizationDecision::isGranted)
                .switchIfEmpty(Mono.defer(() -> {
                    Result<String> rsp = new Result<>(HttpResultEnum.ACCESS_DENIED);
                    String body = null;
                    try {
                        body = objectMapper.writeValueAsString(rsp);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    return Mono.error(new AccessDeniedException(body));
                })).flatMap(d -> Mono.empty());
    }
}
