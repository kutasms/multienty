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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
        long start = System.currentTimeMillis();

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = request.getHeaders();

        String path = request.getURI().getPath();
        if(antPathMatcher.match("/oauth/**", path)) {
            // oauth请求直接放行，授权和鉴权均由其自行决定
            return chain.filter(exchange);
        }

        if(Arrays.stream(properties.getSecurity().getIgnorePaths()).anyMatch(m-> antPathMatcher.match(m, path))) {
            // 忽略路径匹配成功则放行
            return chain.filter(exchange);
        }

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
        if (!hasPermission(token, path)){
            return getVoidMono(response, 403, "无访问权限");
        }
        return chain.filter(exchange).then(Mono.fromRunnable(()-> {
            long end = System.currentTimeMillis();
            if(log.isDebugEnabled()) {
                log.info("实际调用地址为：{}，调用耗时为：{}ms", proxyRequestUri, (end - start));
            }
        }));
    }

    @SneakyThrows
    private boolean hasPermission(String token, String path){
        Claims claims = tokenProvider.getClaims(token);
        ApplicationType applicationType = tokenProvider.getAppType(claims);
        String cacheKey = null;
        switch (applicationType) {
            case PLATFORM:
                cacheKey = String.format(MultientyCacheKey.PATTERN_LOGGED_USER, tokenProvider.getUserId(claims));
                break;
            case TENANT:
                cacheKey = String.format(MultientyCacheKey.PATTERN_LOGGED_TENANT, tokenProvider.getUserId(claims));
                break;
        }
        LoggedUserVO user = stringRedisService.get(cacheKey, LoggedUserVO.class);
        if (Objects.isNull(user)){
            return false;
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

}
