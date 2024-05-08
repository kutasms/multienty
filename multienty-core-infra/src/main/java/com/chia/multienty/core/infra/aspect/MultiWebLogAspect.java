package com.chia.multienty.core.infra.aspect;

import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.annotation.MultiWebLog;
import com.chia.multienty.core.domain.basic.IWebLogUser;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.dubbo.service.DubboMultientyService;
import com.chia.multienty.core.pojo.WebLog;
import com.chia.multienty.core.properties.yaml.YamlMultientyProperties;
import com.chia.multienty.core.service.WebLogService;
import com.chia.multienty.core.tools.MultientyContext;
import com.chia.multienty.core.util.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
/**
 * 注解方式生成日志对象，指定topic生成对象类名
 */
@RequiredArgsConstructor
@Slf4j(topic = "MultiWebLogAspect")
public class MultiWebLogAspect {

    private final WebLogService webLogService;
    private final YamlMultientyProperties properties;
    private long startTime;
    private final DubboMultientyService dubboMultientyService;

    @Pointcut("@annotation(com.chia.multienty.core.annotation.MultiWebLog)")
    public void multiWebLog() {
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Before("multiWebLog()")
    public void before() throws Throwable {
        startTime = System.currentTimeMillis();
    }

    @AfterReturning(returning = "result",pointcut = "multiWebLog()")
    public Object after(JoinPoint jp, Result result) throws Throwable {
        //封装请求记录
        Object[] args = jp.getArgs();
        Object parameter = null;
        if(args != null && args.length > 0) {
            for (Object arg : args) {
                if(arg instanceof MultipartFile
                        || arg instanceof HttpServletRequest
                        || arg instanceof HttpServletResponse) {
                    continue;
                }
                parameter = arg;
            }
        }
        ApiOperation api = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(ApiOperation.class);
        MultiWebLog annoWebLog = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(MultiWebLog.class);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        Object userObj = request.getAttribute("user");
        IWebLogUser user;
        if(userObj != null && request.getAttribute("user") instanceof IWebLogUser) {
            user = (IWebLogUser) request.getAttribute("user");
        } else {
            user = null;
        }
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //浏览器名称
        String browserName = browser.getName();
        List<Long> metaIds = getMetaIds(parameter);
        if(metaIds != null && metaIds.size() > 0) {
            Object finalParameter = parameter;
            List<WebLog> logs = metaIds.stream().map(m -> {
                WebLog webLog = new WebLog();
                webLog.setCreateTime(LocalDateTime.now());
                webLog.setAppName(MultientyContext.getAppType().name());
                webLog.setIp(HttpUtil.getClientIp(request));
                webLog.setUrl(request.getServletPath());
                webLog.setBrowser(browserName);
                webLog.setTarget(annoWebLog.target());
                try {
                    webLog.setArgs(objectMapper.writeValueAsString(finalParameter));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                webLog.setName(user !=null && user.getLogUserName() != null ? user.getLogUserName() : "未知");
                webLog.setUid(user !=null && user.getLogUserId()!=null ? user.getLogUserId() : 0);
                webLog.setMetaId(m);
                webLog.setType((short)annoWebLog.type());
                webLog.setApi(api != null ? api.value() : null);
                webLog.setTarget(annoWebLog.target());
                webLog.setDescription(result.getDescription());
                webLog.setTime(System.currentTimeMillis() - startTime);
                return webLog;
            }).collect(Collectors.toList());
            webLogService.saveBatch(logs);
        }
        else {
            WebLog webLog = new WebLog();
            webLog.setCreateTime(LocalDateTime.now());
            webLog.setAppName(MultientyContext.getAppType().name());
            webLog.setIp(HttpUtil.getClientIp(request));
            webLog.setUrl(request.getServletPath());
            webLog.setBrowser(browserName);
            webLog.setArgs(objectMapper.writeValueAsString(parameter));
            webLog.setName(user.getLogUserName());
            webLog.setUid(user.getLogUserId());
            webLog.setApi(api != null ? api.value() : null);
            webLog.setTarget(annoWebLog.target());
            webLog.setDescription(result.getDescription());
            webLog.setTime(System.currentTimeMillis() - startTime);
            dubboMultientyService.saveWebLog(webLog);
        }
        return result;
    }

    private List<Long> getMetaIds(Object parameter) throws IllegalAccessException {
        if(parameter == null) {
            return null;
        }
        Field[] fields = parameter.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++) {
            Field field = fields[i];
            if(field.isAnnotationPresent(LogMetaId.class)) {
                field.setAccessible(true);
                List<Long> result = null;
                if(field.getType().equals(List.class)) {
                    result = (List<Long>) field.get(parameter);
                } else {
                    result = null;
                }
                field.setAccessible(false);
                return result;
            }
        }
        return null;
    }
}
