package com.chia.multienty.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.constants.MultientyCacheKey;
import com.chia.multienty.core.domain.enums.ApplicationType;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.domain.vo.LoggedUserVO;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.tools.TokenProvider;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class AuthorizeFilter implements GlobalFilter {
    @Autowired
    private StringRedisService stringRedisService;

    @Autowired
    private YamlMultientyProperties properties;

    @Autowired
    private TokenProvider tokenProvider;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final static String HEADER_TOKEN = "X-TOKEN";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI proxyRequestUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
//        Set<URI> origReqUrlAttr = (LinkedHashSet<URI>)exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
//        String origUrl = origReqUrlAttr.stream().findFirst().get().getPath();
        long start = System.currentTimeMillis();

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = request.getHeaders();

        String path = request.getURI().getPath();
        if(antPathMatcher.match("/oauth/**", path)) {
            // oauth请求直接放行，授权和鉴权均由其自行决定
            return wrapMono(exchange, chain, proxyRequestUri, start);
        }

        if(Arrays.stream(properties.getSecurity().getIgnorePaths()).anyMatch(m-> antPathMatcher.match(m, path))) {
            log.info("忽略路径匹配成功则放行,{}", path, proxyRequestUri.toString());
            // 忽略路径匹配成功则放行
            return wrapMono(exchange, chain, proxyRequestUri, start);
        }
        //放行静态资源
        String token = headers.getFirst(HEADER_TOKEN);
        if(Strings.isEmpty(token)) {
            // 如果请求的header中没有token,则报401 UNAUTHORIZED错误
            Result<String> result = new Result<>(HttpResultEnum.UNAUTHORIZED);
            String json = JSONObject.toJSONString(result);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            DataBuffer buffer = response.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        }

        log.info("request path:{}, URI:{}", path, proxyRequestUri.toString());

        Claims claims = tokenProvider.getClaims(token);
        if(!tokenProvider.validateToken(token)) {
            return getVoidMono(response, HttpResultEnum.TOKEN_EXPIRED);
        }


        // 无需权限验证的操作
        if(Arrays.stream(properties.getSecurity().getIgnoreVerifyPermissions())
                .anyMatch(m-> antPathMatcher.match(m, path))) {
            // 忽略路径匹配成功则放行
            return wrapMono(exchange, chain, proxyRequestUri, start);
        }
        if (!hasPermission(claims, path)){
            return getVoidMono(response, HttpResultEnum.ACCESS_DENIED);
        }
        return wrapMono(exchange, chain, proxyRequestUri, start);
    }

    private Mono<Void> wrapMono(ServerWebExchange exchange, GatewayFilterChain chain, URI proxyRequestUri, long start) {

        return chain.filter(exchange).then(Mono.fromRunnable(()-> {
            long end = System.currentTimeMillis();
            if(log.isDebugEnabled()) {
                log.info("实际调用地址为：{}，调用耗时为：{}ms", proxyRequestUri, (end - start));
            }
        }));
    }

    @SneakyThrows
    private boolean hasPermission(Claims claims, String path){
        ApplicationType applicationType = tokenProvider.getAppType(claims);
        String cacheKey = null;
        switch (applicationType) {
            case PLATFORM:
                cacheKey = String.format(MultientyCacheKey.PATTERN_LOGGED_USER, tokenProvider.getUserId(claims));
                break;
            case MERCHANT:
                cacheKey = String.format(MultientyCacheKey.PATTERN_LOGGED_TENANT, tokenProvider.getUserId(claims));
                break;
            case WECHAT_MPP:
                // 微信小程序暂时全面开放权限
                return true;
        }
        LoggedUserVO user = stringRedisService.get(cacheKey, LoggedUserVO.class);
        if (Objects.isNull(user)){
            return false;
        }

        if(user.getSuperAdmin() != null && user.getSuperAdmin()) {
            return true;
        }

        List<String> apis = user.getPermissions().stream()
                .filter(p -> StringUtils.hasText(p.getApi()))
                .map(m -> m.getApi())
                .collect(Collectors.toList());

        return apis.stream().anyMatch(api
                -> antPathMatcher.match(api, path));
    }

    private Mono<Void> getVoidMono(ServerHttpResponse response, int i, String msg) {
        // 构造错误响应体
        Result<String> result = new Result<>(i, msg);
        response.setStatusCode(HttpStatus.FORBIDDEN);
        // 将错误响应体转换为JSON字符串
        String json = JSONObject.toJSONString(result);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer buffer = response.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    private Mono<Void> getVoidMono(ServerHttpResponse response, HttpResultEnum httpResultEnum) {
        // 构造错误响应体
        Result<String> result = new Result<>(httpResultEnum);
        response.setStatusCode(HttpStatus.FORBIDDEN);
        // 将错误响应体转换为JSON字符串
        String json = JSONObject.toJSONString(result);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        DataBuffer buffer = response.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

}
