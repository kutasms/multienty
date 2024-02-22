package com.chia.multienty.core.aspect;

import com.alibaba.fastjson.JSONObject;

import com.chia.multienty.core.cache.redis.service.api.HashRedisService;
import com.chia.multienty.core.cache.redis.service.api.ListRedisService;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.annotation.Cached;
import com.chia.multienty.core.domain.basic.Result;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Aspect
@Component
/**
 * 注解方式生成日志对象，指定topic生成对象类名
 */
@Slf4j(topic = "CachedAspect")
@RequiredArgsConstructor
public class CachedAspect {

    private final StringRedisService stringRedisService;
    private final ListRedisService listRedisService;
    private final HashRedisService hashRedisService;
    private final ObjectMapper objectMapper;
    @Pointcut("@annotation(com.chia.multienty.core.annotation.Cached)")
    public void pointCut() {
    }

    private String getPostParams(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = request.getReader()) {
            char[] buff = new char[1024];
            int len;
            while((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (IOException e) {
            log.error("获取POST请求参数时发生故障",e);
        }
        return sb.toString();
    }



    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Assert.notNull(request, "request can not null");

        String cacheKey = null;
        if(request.getMethod().equals("POST")) {

            cacheKey = String.format("%s-%s",request.getServletPath(), JSONObject.toJSONString(pjp.getArgs()));
        } else{
            cacheKey = String.format("%s-%s",request.getServletPath(),request.getQueryString());
        }
        log.info("从缓存中加载数据,cacheKey:{}", cacheKey);
        Cached cached = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(Cached.class);
        Cached.Type type = cached.type();
        Object result = null;
        switch (type) {
            case LIST:
                List<Object> list = listRedisService.lGet(cacheKey);
                if(list != null) {
                    result = new Result<>(list);
                } else{
                    result = pjp.proceed();
                    listRedisService.lSet(cacheKey,(List<Object>)result, cached.timeout());
                }
                break;
            case STRING:
                Object str = stringRedisService.get(cacheKey);
                if(str != null) {
                    result =  objectMapper.readValue(str.toString(), new TypeReference<Result<?>>() {});

                } else{
                    result =  pjp.proceed();
                    stringRedisService.set(cacheKey, objectMapper.writeValueAsString(result), cached.timeout());
                }
                break;
            case HASHMAP:
                Map map = hashRedisService.hgetAll(cacheKey);
                if(map != null) {
                    result =  new Result<>(map);
                } else{
                    Result<Map> rsp =  (Result<Map>)pjp.proceed();
                    if(rsp.getCode().equals("")) {
                        hashRedisService.hmset(cacheKey, rsp.getData(), cached.timeout());
                        result = rsp;
                    } else{
                        return rsp;
                    }
                }
                break;
        }
        return result;
    }
}
