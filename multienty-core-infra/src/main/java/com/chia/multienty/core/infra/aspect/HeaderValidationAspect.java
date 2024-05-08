package com.chia.multienty.core.infra.aspect;

import com.chia.multienty.core.annotation.ValidateHeader;
import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
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

@Aspect
@Component
@Slf4j(topic = "CachedAspect")
@RequiredArgsConstructor
public class HeaderValidationAspect {

    @Pointcut("@annotation(com.chia.multienty.core.annotation.ValidateHeader)")
    public void pointCut() {
    }
    @Around("pointCut()")
    public Object validateHeaders(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Assert.notNull(request, "request can not null");
        ValidateHeader validateHeader = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(ValidateHeader.class);
        String result = isHeaderPresentAndNotEmpty(request, validateHeader);
        if (result != null) {
            throw new KutaRuntimeException(HttpResultEnum.ARG_LOSE_PATTERN, result);
        } else {
            return joinPoint.proceed();
        }
    }


    private String isHeaderPresentAndNotEmpty(HttpServletRequest request, ValidateHeader validateHeader) {
        String headNames = validateHeader.headNames();
        if(headNames.contains(",")) {
            String[] splitArr = headNames.split(",");
            for (String s : splitArr) {
                if(s.trim().isEmpty() || request.getHeader(s.trim()) == null) {
                    return s;
                }
            }
        } else {
            if(headNames.trim().isEmpty() || request.getHeader(headNames.trim()) == null) {
                return headNames;
            }
        }
        return null;
    }
}
