package com.chia.multienty.core.util;

import lombok.SneakyThrows;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.context.ApplicationContext;

public class SpringUtil {

    private static volatile ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static AnnotationConfigServletWebApplicationContext getAnnotationConfigServAppContext() {
        if(context instanceof AnnotationConfigServletWebApplicationContext) {
            return (AnnotationConfigServletWebApplicationContext) context;
        }
        throw new RuntimeException("The current application context is not AnnotationConfigServletWebApplicationContext.");
    }

    public static void setContext(ApplicationContext applicationContext) {
        if (context == null) {
            context = applicationContext;
        }
    }

    public static Object getBean(String name){
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        if(context == null) {
            return null;
        }
        return context.getBean(clazz);
    }
    public static <T> T registerBean(Class<T> clazz) {
        return context.getAutowireCapableBeanFactory().createBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return context.getBean(name, clazz);
    }

    public static <T> T getBean(Class<T> clazz, boolean ensureBeanExists) {
        if(ensureBeanExists) {
            if (context.containsBean(clazz.getName())) {
                return context.getBean(clazz);
            } else {
                return registerBean(clazz);
            }
        } else {
            return context.getBean(clazz);
        }
    }

    @SneakyThrows
    public static <T> T getBean(String className, boolean ensureBeanExists) {
        if(ensureBeanExists) {
            if (context.containsBean(className)) {
                return (T)context.getBean(className);
            } else {
                return (T)registerBean(Class.forName(className));
            }
        } else {
            return (T)context.getBean(className);
        }
    }

    public static <T> T getBeanOrCreate(Class<T> clazz) {
        return getBean(clazz, true);
    }

    public static <T> T getBeanOrCreate(String className) {
        return getBean(className, true);
    }


    public static boolean isLinux() {
       return context.getEnvironment().getProperty("os.name").toLowerCase().contains("linux");
    }
}
