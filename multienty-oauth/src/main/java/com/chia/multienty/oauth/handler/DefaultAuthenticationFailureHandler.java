//package com.chia.multienty.oauth.handler;
//
//import com.chia.multienty.core.domain.basic.Result;
//import com.chia.multienty.core.domain.enums.HttpResultEnum;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferFactory;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.server.WebFilterExchange;
//import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//@Component
//@RequiredArgsConstructor
//public class DefaultAuthenticationFailureHandler implements ServerAuthenticationFailureHandler {
//
//    private final ObjectMapper objectMapper;
//    @Override
//    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
//        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange()
//                .getResponse()).flatMap(response -> {
//            DataBufferFactory dataBufferFactory = response.bufferFactory();
//            Result<String> result = null;
//            // 账号不存在
//            if (exception instanceof UsernameNotFoundException) {
//                result = new Result<>(HttpResultEnum.ACCOUNT_NOT_EXIST);
//                // 用户名或密码错误
//            } else if (exception instanceof BadCredentialsException) {
//                result = new Result<>(HttpResultEnum.PASSWORD_ERROR);
//                // 账号已过期
//            } else if (exception instanceof AccountExpiredException) {
//                result = new Result<>(HttpResultEnum.ACCOUNT_EXPIRED);
//                // 账号已被锁定
//            } else if (exception instanceof LockedException) {
//                result = new Result<>(HttpResultEnum.USER_ACCOUNT_LOCKED);
//                // 用户凭证已失效
//            } else if (exception instanceof CredentialsExpiredException) {
//                result = new Result<>(HttpResultEnum.TOKEN_EXPIRED);
//                // 账号已被禁用
//            } else if (exception instanceof DisabledException) {
//                result = new Result<>(HttpResultEnum.USER_STATE_DISABLED);
//            }
//            DataBuffer dataBuffer = null;
//            try {
//                dataBuffer = dataBufferFactory.wrap(objectMapper.writeValueAsString(result).getBytes());
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//            return response.writeWith(Mono.just(dataBuffer));
//        }));
//    }
//}
