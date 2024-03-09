//package com.chia.multienty.oauth.handler;
//
//import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
//import com.chia.multienty.core.domain.vo.LoggedUserVO;
//import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
//import com.chia.multienty.core.service.UserService;
//import com.chia.multienty.core.tools.TokenProvider;
//import com.chia.multienty.oauth.domain.bo.OAuthDetails;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.core.io.buffer.DataBufferFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.server.WebFilterExchange;
//import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//@Component
//@RequiredArgsConstructor
//public class DefaultAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
//
//    private final TokenProvider tokenProvider;
//    private final YamlMultientyProperties properties;
//    private final StringRedisService stringRedisService;
//    private final ObjectMapper objectMapper;
//    private final UserService userService;
//    @Override
//    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
//        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange().getResponse()).flatMap(response -> {
//            DataBufferFactory dataBufferFactory = response.bufferFactory();
//            // 生成JWT token
//            LoggedUserVO userDetails = (LoggedUserVO) authentication.getPrincipal();
//            String accessToken = tokenProvider.createToken(userDetails.getUserId(), userDetails.getUsername(), userDetails.getRoles());
//            String refreshToken = tokenProvider.createRefreshToken(userDetails.getUserId(), userDetails.getUsername());
//            OAuthDetails authInfo = OAuthDetails.builder()
//                    .accessToken(accessToken)
//                    .expire(properties.getSecurity().getAuth().getAccessTokenExpired())
//                    .refreshToken(refreshToken)
//                    .refreshExpire(properties.getSecurity().getAuth().getRefreshTokenExpired())
//                    .user(userDetails)
//                    .build();
//            DataBuffer dataBuffer = null;
//            try {
//                String json = objectMapper.writeValueAsString(authInfo);
//                String cacheKey = userService.getCacheKey(userDetails.getUserId());
//                stringRedisService.set(cacheKey, json, authInfo.getExpire() * 1000);
//                dataBuffer = dataBufferFactory.wrap(json.getBytes());
//            } catch (JsonProcessingException e) {
//                throw new RuntimeException(e);
//            }
//
//            return response.writeWith(Mono.just(dataBuffer));
//        }));
//    }
//}
