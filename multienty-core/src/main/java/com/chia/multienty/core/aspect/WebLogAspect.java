package com.chia.multienty.core.aspect;

import com.chia.multienty.core.annotation.WebLog;
import com.chia.multienty.core.annotation.LogMetaId;
import com.chia.multienty.core.domain.basic.IWebLogUser;
import com.chia.multienty.core.domain.basic.Result;
import com.chia.multienty.core.domain.basic.WebLogUser;
import com.chia.multienty.core.dubbo.service.DubboMultiTenantService;
import com.chia.multienty.core.properties.yaml.YamlMultiTenantProperties;
import com.chia.multienty.core.service.WebLogService;
import com.chia.multienty.core.tools.MultiTenantContext;
import com.chia.multienty.core.util.HttpUtil;
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

@Aspect
@Component
/**
 * 注解方式生成日志对象，指定topic生成对象类名
 */
@RequiredArgsConstructor
@Slf4j(topic = "WebLogAspect")
public class WebLogAspect {
    private final WebLogService webLogService;
    private final YamlMultiTenantProperties properties;
    private long startTime;

    private final DubboMultiTenantService dubboMultiTenantService;

    @Pointcut("@annotation(com.chia.multienty.core.annotation.WebLog)")
    public void webLog() {
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Before("webLog()")
    public void before() throws Throwable {
        startTime = System.currentTimeMillis();
    }

    @AfterReturning(returning = "result",pointcut = "webLog()")
    public Object after(JoinPoint jp,Result result) throws Throwable {
        //封装请求记录
        com.chia.multienty.core.pojo.WebLog webLog = new com.chia.multienty.core.pojo.WebLog();
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
        ApiOperation annoApi = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(ApiOperation.class);
        WebLog annoWebLog = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(
                WebLog.class);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        Object userObj = request.getAttribute("user");
        IWebLogUser user;
        if(userObj != null && request.getAttribute("user") instanceof IWebLogUser) {
            user = (IWebLogUser) request.getAttribute("user");
        } else {
            user = WebLogUser.getInstance();
        }
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //浏览器名称
        String browserName = browser.getName();
        webLog.setCreateTime(LocalDateTime.now());
        webLog.setAppName(MultiTenantContext.getAppType().name());
        webLog.setIp(HttpUtil.getClientIp(request));
        webLog.setUrl(request.getServletPath());
        webLog.setBrowser(browserName);
        webLog.setType((short) annoWebLog.type());
        webLog.setArgs(objectMapper.writeValueAsString(parameter));
        webLog.setName(user !=null && user.getUserName() != null ? user.getUserName() : "未知");
        webLog.setUid(user !=null && user.getUserId()!=null ? user.getUserId() : 0);
        webLog.setMetaId(getMetaId(parameter));
        webLog.setApi(annoApi != null ? annoApi.value() : null);
        webLog.setDescription(result.getDescription());
        webLog.setTime(System.currentTimeMillis() - startTime);
        dubboMultiTenantService.saveWebLog(webLog);
        return result;
    }

    private Long getMetaId(Object parameter) throws IllegalAccessException {
        if(parameter == null) {
            return null;
        }
        Field[] fields = parameter.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++) {
            Field field = fields[i];
            if(field.isAnnotationPresent(LogMetaId.class)) {
                field.setAccessible(true);
                Long result = null;
                if(field.getType().equals(Long.class)
                        || field.getType().equals(Integer.class)
                        || field.getType().equals(Short.class)
                        || field.getType().equals(Byte.class)) {
                    result = (Long) field.get(parameter);
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
