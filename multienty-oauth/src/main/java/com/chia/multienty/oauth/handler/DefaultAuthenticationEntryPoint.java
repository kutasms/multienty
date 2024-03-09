package com.chia.multienty.oauth.handler;

import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

@Component
@RequiredArgsConstructor
public class DefaultAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        return Mono.defer(() -> Mono.just(exchange.getResponse())).flatMap(response -> {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            Result<String> rsp = new Result<>(HttpResultEnum.UNAUTHORIZED);
            String result = null;
            try {
                result = objectMapper.writeValueAsString(rsp);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            DataBuffer buffer = dataBufferFactory.wrap(result.getBytes(
                    Charset.defaultCharset()));
            return response.writeWith(Mono.just(buffer));
        });
    }
}
