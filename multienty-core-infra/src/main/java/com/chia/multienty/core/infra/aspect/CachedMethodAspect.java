package com.chia.multienty.core.infra.aspect;

import com.chia.multienty.core.cache.redis.service.api.HashRedisService;
import com.chia.multienty.core.cache.redis.service.api.ListRedisService;
import com.chia.multienty.core.cache.redis.service.api.StringRedisService;
import com.chia.multienty.core.domain.constants.MultientyConstants;
import com.chia.multienty.core.util.KutaBeanUtil;
import com.chia.multienty.core.annotation.CachedMethod;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Aspect
@Component
/**
 * 注解方式生成日志对象，指定topic生成对象类名
 */
@Slf4j
@RequiredArgsConstructor
public class CachedMethodAspect {
    private final StringRedisService stringRedisService;
    private final ListRedisService listRedisService;
    private final HashRedisService hashRedisService;

    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("@annotation(com.chia.multienty.core.annotation.CachedMethod)")
    public void pointCut() {
    }


    @Around("pointCut()")
    public Object before(ProceedingJoinPoint pjp) throws Throwable {
        CachedMethod cachedMethod = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(CachedMethod.class);
        Object targetObj =pjp.getTarget();
        String methodName = pjp.getSignature().getName();

        Object[] args = pjp.getArgs();
        String joinedArgNames = (args == null || args.length == 0)
                ? "NULL" : (
                Arrays.stream(args).map(m->
                        {
                            if(m.getClass().isPrimitive() || m instanceof String) {
                                return m.toString();
                            } else {
                                try {
                                    return objectMapper.writeValueAsString(m);
                                } catch (JsonProcessingException e) {
                                    return m.toString();
                                }
                            }
                        }
                ).collect(Collectors.joining("-"))
        );
        String cacheKey = cachedMethod.cacheKeyPrefix().equals(MultientyConstants.DEFAULT_CACHE_METHOD_KEY_PREFIX)
                ? String.format("%s-%s-%s-%s",
                cachedMethod.cacheKeyPrefix(),
                targetObj.getClass().getName(),
                methodName,
                joinedArgNames) : String.format("%s-%s", cachedMethod.cacheKeyPrefix(),joinedArgNames);
        log.debug("方法缓存键:{}", cacheKey);
        CachedMethod.Type type = cachedMethod.type();
        Object result = null;
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        switch (type) {
            case LIST:
                List<Object> list = listRedisService.lGet(cacheKey);
                if(list != null) {
                    result = list;
                } else{
                    list = (List<Object>)pjp.proceed();
                    listRedisService.lSet(cacheKey, list, cachedMethod.timeout());
                    return list;
                }
                break;
            case STRING:
                Object str = stringRedisService.get(cacheKey);
                if(str != null) {
                    if(!Collection.class.isAssignableFrom(method.getReturnType())) {
                        result =  objectMapper.readValue(str.toString(), method.getReturnType());
                        log.debug("从缓存中加载非列表数据:{}", objectMapper.writeValueAsString(result));
                    } else {
                        Class<?> returnType = method.getReturnType();
                        Type superClass = method.getGenericReturnType();
                        Type[] rawTypes = ((ParameterizedType) superClass).getActualTypeArguments();
                        List<Class<?>> parameterClazzList = Arrays.stream(rawTypes).map(m -> (Class<?>)m).collect(Collectors.toList());
                        Class<?>[] rawClazzArray = new Class<?>[rawTypes.length];
                        rawClazzArray = parameterClazzList.toArray(rawClazzArray);
                        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(returnType, rawClazzArray);
                        result =  objectMapper.readValue(str.toString(), javaType);
                    }
                    log.debug("从缓存中加载列表数据:{}", objectMapper.writeValueAsString(result));
                } else{
                    result =  pjp.proceed();
                    String formatted = objectMapper.writeValueAsString(result);
                    stringRedisService.set(cacheKey, formatted, cachedMethod.timeout());
                    log.debug("从原始方法中加载数据:{}", objectMapper.writeValueAsString(result));
                    return result;
                }
                break;
            case HASHMAP:
                Map<String, Object> map = hashRedisService.hgetAll(cacheKey);
                if(map != null) {
                    result = KutaBeanUtil.mapToBean(map, method.getReturnType());
                } else{
                    result =  pjp.proceed();
                    Map<String, Object> formattedMap = KutaBeanUtil.bean2Map(result);
                    if(result != null) {
                        hashRedisService.hmset(cacheKey, formattedMap, cachedMethod.timeout());
                    } else {
                        hashRedisService.hmset(cacheKey, formattedMap, cachedMethod.timeout());
                    }
                    return result;
                }
                break;
        }
        return result;
    }
}
