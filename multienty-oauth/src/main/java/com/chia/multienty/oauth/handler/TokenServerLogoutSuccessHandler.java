package com.chia.multienty.oauth.handler;

import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.UserService;
import com.chia.multienty.core.tools.TokenProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TokenServerLogoutSuccessHandler implements ServerLogoutSuccessHandler {

    private final StringRedisService stringRedisService;
    private final YamlMultientyProperties properties;

    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper;
    private final UserService userService;
    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        return Mono.defer(() -> Mono.just(exchange.getExchange().getResponse()).flatMap(response-> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            String token = exchange.getExchange().getRequest().getHeaders().getFirst(
                    properties.getSecurity().getAuth().getHeader()
            );
            Claims claims = tokenProvider.getClaims(token);
            if (!Objects.isNull(claims)) {
                stringRedisService.delete(userService.getCacheKey(tokenProvider.getUserId(token)));
            }
            Result<String> result = new Result<>(HttpResultEnum.SUCCESS);

            DataBuffer dataBuffer = null;
            try {
                dataBuffer = dataBufferFactory.wrap(objectMapper.writeValueAsBytes(result));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}
